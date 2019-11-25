package com.orange.module_base.utils.statusbar

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager


private const val STATUSBAR_TYPE_DEFAULT = 0x00 //系统默认的状态栏
private const val STATUSBAR_TYPE_MIUI = 0x01
private const val STATUSBAR_TYPE_FLYME = 0x02
private const val STATUSBAR_TYPE_ANDROID6 = 0x03
private const val STATUSBAR_DEFAULT_HEIGHT = 24 //状态栏的高度一般为24~25dp，小米8se的刘海为40dp
private const val TAG = "StatusBarUtil"


/**当前状态栏类型**/
private var statusBarType = STATUSBAR_TYPE_DEFAULT


/**
 * 获取状态栏高度，返回值单位为px
 */
fun Context.getStatusBarHeight(): Int {
    val height = STATUSBAR_DEFAULT_HEIGHT
    val resId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (resId > 0) {
        resources.getDimensionPixelSize(resId)
    } else {
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                height.toFloat(), Resources.getSystem().displayMetrics).toInt()
    }
}

/**
 * 设置状态栏颜色
 * 顶部为图片的透明状态栏的根布局android:fitsSystemWindows必须为false
 */
fun Activity.setStatusBarColor(@ColorRes color: Int, isFullScreen: Boolean = false) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
        //4.4以下暂不考虑沉浸式
        return
    }

    //全屏时使用小米魅族自己的适配方案，可以适配到4.4
    if (isFullScreen && (isMIUI || isFlyme)) {
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        return
    }
    //非小米魅族的手机适配方案
    when {
    //5.0以上的版本
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
            window.decorView.systemUiVisibility =
                    if (isFullScreen) View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    else View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, color)
        }
    //4.4及4.4以上，5.0以下
        Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP -> {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            setStatusBarView(window.decorView as ViewGroup, colorId = color, isFullScreen = isFullScreen)
        }
    }
}

/**
 * 渐变色状态栏
 */
fun Activity.setGradientStatusBar(@DrawableRes drawableId: Int) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
        //4.4以下暂不考虑沉浸式
        return
    }
    when {
    //在华为7.0的手机上状态栏是半透明的，所以统一把Android5.0以上的状态栏设为透明
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
        -> window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        else -> window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }
    setStatusBarView(window.decorView as ViewGroup, drawableId = drawableId, isFullScreen = false)
}

/**
 * 透明状态栏
 * 由于透明状态栏一般是需要全屏的，所以isFullScreen默认为true
 */
fun Activity.setTransParentStatusBar(isFullScreen: Boolean = true) {
    setStatusBarColor(android.R.color.transparent, isFullScreen)
}

/**
 *
 * @param colorId 不为0时，设置纯色状态栏：仅用于Android4.4版本
 * @param drawableId 不为0时，设置渐变色状态栏，适用于各个版本，优先于colorId
 *
 */
fun Activity.setStatusBarView(container: ViewGroup,
                              @ColorRes colorId: Int = 0,
                              @DrawableRes drawableId: Int = 0,
                              isFullScreen: Boolean = false) {
    fun View.setStatusBackground() {
        if (drawableId != 0) {
            setBackgroundResource(drawableId)
        } else {
            setBackgroundColor(ContextCompat.getColor(container.context, colorId))
        }
    }

    var barView: View? = container.findViewById(android.R.id.custom)
    when (barView == null) {
        true -> {
            barView = View(container.context)
            barView.id = android.R.id.custom
            //在这里设置颜色之后，根布局可以不必设置背景色，只需设置android:fitsSystemWindows为true
            barView.setStatusBackground()
            val lp = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, container.context.getStatusBarHeight())
            container.addView(barView, lp)
        }
        false -> barView?.setStatusBackground()
    }
    if (!isFullScreen) {
        //该方法相当于根布局设置android:fitsSystemWindows为true
        val contentView = findViewById<ViewGroup>(android.R.id.content)
        contentView?.let {
            it.getChildAt(0).fitsSystemWindows = true
        }
    }


}


/**
 * 设置状态栏白色字体图标
 * 支持 4.4 以上版本 MIUI 和 Flyme，以及 6.0 以上版本的其他 Android
 */
@TargetApi(Build.VERSION_CODES.M)
fun Activity.setStatusBarDarkMode(): Boolean {
    if (statusBarType == STATUSBAR_TYPE_DEFAULT) {
        //当前已经是默认的，不必设置
        return true
    }

    return when (statusBarType) {
        STATUSBAR_TYPE_MIUI -> window.setMIUIStatusBarMode(false)
        STATUSBAR_TYPE_FLYME -> window.setFLYMEStatusBarMode(false)
        STATUSBAR_TYPE_DEFAULT -> window.setAndroid6StatusBarMode(false)
        else -> window.setAndroid6StatusBarMode(false)
    }
}


/**
 * 设置状态栏字体图标为深色模式，需要 MIUIV6 以上
 * @param isLightMode 是否把状态栏字体及图标颜色设置为深色
 */
@SuppressLint("PrivateApi")
private fun Window.setMIUIStatusBarMode(isLightMode: Boolean): Boolean {
    if (isMIUI6BELOW) {
        Log.e(TAG, "当前MIUI版本低于6")
        return false
    }
    val clazz = javaClass
    return try {
        val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
        val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
        val darkModeFlag = field.getInt(layoutParams)
        val extraFlagField = clazz.getMethod("setExtraFlags", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
        when (isLightMode) {
            true -> extraFlagField.invoke(this, darkModeFlag, darkModeFlag) ////状态栏透明且黑色字体
            false -> extraFlagField.invoke(this, 0, darkModeFlag)
        }
        true
    } catch (ignored: Exception) {
        Log.e(TAG, "MIUI设置深色风格失败")
        false
    }
}

/**
 * 设置状态栏图标为深色和魅族特定的文字风格
 * 可以用来判断是否为 Flyme 用户
 * @param isLightMode 是否把状态栏字体及图标颜色设置为深色
 */
@RequiresApi(Build.VERSION_CODES.M)
private fun Window.setFLYMEStatusBarMode(isLightMode: Boolean): Boolean {
    return try {
        if (isFlyme6Above) {
            //flyme 在 6.2.0.0A 支持了 Android 官方的实现方案，旧的方案失效
            setAndroid6StatusBarMode(isLightMode)
        } else {
            val layoutParams = this.attributes
            val darkFlag = WindowManager.LayoutParams::class.java.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
            val meiZuFlags = WindowManager.LayoutParams::class.java.getDeclaredField("meizuFlags")
            darkFlag.isAccessible = true
            meiZuFlags.isAccessible = true
            val bit = darkFlag.getInt(null)
            var value = meiZuFlags.getInt(layoutParams)
            value = if (isLightMode) value or bit else value and bit.inv()
            meiZuFlags.setInt(layoutParams, value)
            this.attributes = layoutParams
            true
        }
    } catch (exception: Exception) {
        Log.e(TAG, "Flyme设置深色风格失败")
        false
    }
}


/**
 * Android 6设置状态栏字体图标为深色，
 * @param isLightMode 是否把状态栏字体及图标颜色设置为深色
 */
@RequiresApi(Build.VERSION_CODES.M)
private fun Window.setAndroid6StatusBarMode(isLightMode: Boolean): Boolean {
    /*if (!isMIUI9ABOVE) {
        //Android6.0 以上
        return setMIUIStatusBarMode(isLightMode)
    }*/
    var systemUiVisibility = if (isLightMode) {
        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    } else {
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
    systemUiVisibility = changeStatusBarModeRetainFlag(this,systemUiVisibility)
    this.decorView.systemUiVisibility = systemUiVisibility
    return true
}

/**
 * 此方法可以解决设置字体为深色模式时自动出现fitsSystemWindows为true的问题
 */
private fun changeStatusBarModeRetainFlag(window: Window, out: Int) :Int{
    var systemUi = out
    fun retainSystemUiFlag(type: Int):Int {
        val now = window.decorView.systemUiVisibility
        if (now and type == type) {
            systemUi = out or type
        }
        return systemUi
    }
    systemUi = retainSystemUiFlag(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    systemUi = retainSystemUiFlag(View.SYSTEM_UI_FLAG_FULLSCREEN)
    systemUi = retainSystemUiFlag(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    systemUi = retainSystemUiFlag(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    systemUi = retainSystemUiFlag(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    systemUi = retainSystemUiFlag(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
    return systemUi
}

/**
 *  设置状态栏字体图标为浅色模式
 */
@TargetApi(Build.VERSION_CODES.M)
fun Activity.setStatusBarLightMode(): Boolean {
    //MIUI9.0以下版本使用小米自己的解决方法，MIUI9 && Android 6 之后用回Android原生实现
    val isMIUICustomStatusBar: Boolean = (isMIUI9ABOVE && Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            || isMIUI9BELOW

    //已知系统类型时，设置状态栏黑色字体图标
    fun setStatusBarLightModeWithType(): Boolean {
        return when (statusBarType) {
            STATUSBAR_TYPE_MIUI -> window.setMIUIStatusBarMode(true)
            STATUSBAR_TYPE_FLYME -> window.setFLYMEStatusBarMode(true)
            STATUSBAR_TYPE_ANDROID6 -> window.setAndroid6StatusBarMode(true)
            else -> {
                false
            }
        }
    }

    if (statusBarType != STATUSBAR_TYPE_DEFAULT) {
        return setStatusBarLightModeWithType()
    }

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        when {
            isMIUICustomStatusBar && window.setMIUIStatusBarMode(true) -> {
                statusBarType = STATUSBAR_TYPE_MIUI
                true
            }
            window.setFLYMEStatusBarMode(true) -> {
                statusBarType = STATUSBAR_TYPE_FLYME
                true
            }
            window.setAndroid6StatusBarMode(true) -> {
                statusBarType = STATUSBAR_TYPE_ANDROID6
                true
            }

            else -> {
                false
            }
        }
    else false
}















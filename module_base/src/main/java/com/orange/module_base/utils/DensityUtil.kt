package com.orange.module_base.utils

import android.content.Context


fun Context.dp2px(dpValue: Int): Int {
    val scale = resources.displayMetrics.density
    return (dpValue * scale + 0.5F).toInt()
}

fun Context.px2dp(pxValue: Int): Int {
    val scale = resources.displayMetrics.density
    return (pxValue / scale + 0.5F).toInt()
}

fun Context.sp2px(spValue: Int): Int {
    val fontScale = resources.displayMetrics.scaledDensity
    return (spValue * fontScale + 0.5F).toInt()
}

fun Context.px2sp(pxValue: Int):Int{
    val fontScale = resources.displayMetrics.scaledDensity
    return (pxValue / fontScale + 0.5F).toInt()
}

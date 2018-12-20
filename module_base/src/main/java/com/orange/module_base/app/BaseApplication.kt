package com.orange.module_base.app

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.orange.module_base.BuildConfig
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger



/**
 * created by czh on 2018/12/10
 */
open class BaseApplication: Application() {

    companion object {
        private var application:BaseApplication?=null
        fun getApplication()= application
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        application=this
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        initARouter()

        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun log(priority: Int, tag: String?, message: String) {
                if (tag==null){
                    super.log(priority, "Daily", message)
                }else{
                    super.log(priority, tag, message)
                }
            }

            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return super.isLoggable(priority, tag)
            }
        })
    }



    private fun initARouter(){
        if (BuildConfig.DEBUG) {
            ARouter.openLog()  // 打印日志
            ARouter.openDebug() // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)// 尽可能早，推荐在Application中初始化
    }
}
package com.orange.module_base.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import com.alibaba.android.arouter.launcher.ARouter
import com.orange.module_base.base.bean.BaseBean
import java.util.ArrayList
import java.util.HashMap

/**
 * created by czh on 2018/12/10
 */
class ActivityJumper{

    companion object {
        /**
         * 普通Activity之间跳转
         *
         * @param activity activity
         * @param clazz    目标activity
         */
        fun jumpActivity(activity: Context, clazz: Class<out Activity>) {
            jumpActivity(activity, clazz, null)
        }

        /**
         * 普通Activity之间跳转
         *
         * @param activity activity
         * @param clazz    目标activity
         * @param params   参数
         */
        fun jumpActivity(activity: Context, clazz: Class<out Activity>,
                       params: Map<String, *>?) {
            val intent = Intent(activity, clazz)
            if (params != null) {
                for ((key) in params) {
                    val value = params[key]
                    if (value is String) {
                        intent.putExtra(key, value)
                    } else if (value is Boolean) {
                        intent.putExtra(key, value)
                    } else if (value is Int) {
                        intent.putExtra(key, value)
                    } else if (value is Float) {
                        intent.putExtra(key, value)
                    } else if (value is Double) {
                        intent.putExtra(key, value)
                    } else if (value is Long) {
                        intent.putExtra(key, value)
                    } else if (value is Short) {
                        intent.putExtra(key, value)
                    } else if (value is BaseBean) {
                        intent.putExtra(key, value as BaseBean)
                    } else if (value is ArrayList<*>) {
                        intent.putExtra(key, value)
                    } else if (value is HashMap<*, *>) {
                        intent.putExtra(key, value)
                    }
                }
            }
            activity.startActivity(intent)
        }


        /**
         * ARouter跳转Activity
         *
         * @param url 目标Activity Url
         */
        fun jumpActivity(url: String) {
            jumpActivity(url, null)
        }

        /**
         * ARouter跳转Activity
         *
         * @param url    目标Activity Url
         * @param params 参数
         */
        fun jumpActivity(url: String, params: Map<String, *>?) {
            if (TextUtils.isEmpty(url)) {
                return
            }
            val postcard = ARouter.getInstance()
                    .build(url)
            if (params != null) {
                for ((key) in params) {
                    val value = params[key]
                    if (value is String) {
                        postcard.withString(key, value)
                    } else if (value is Boolean) {
                        postcard.withBoolean(key, value)
                    } else if (value is Int) {
                        postcard.withInt(key, value)
                    } else if (value is Float) {
                        postcard.withFloat(key, value)
                    } else if (value is Double) {
                        postcard.withDouble(key, value)
                    } else if (value is Long) {
                        postcard.withLong(key, value)
                    } else if (value is Short) {
                        postcard.withShort(key, value)
                    } else if (value is BaseBean) {
                        postcard.withSerializable(key, value as BaseBean)
                    } else if (value is ArrayList<*>) {
                        postcard.withSerializable(key, value)
                    } else if (value is HashMap<*, *>) {
                        postcard.withSerializable(key, value)
                    }
                }
            }
            postcard.navigation()
        }
    }

}
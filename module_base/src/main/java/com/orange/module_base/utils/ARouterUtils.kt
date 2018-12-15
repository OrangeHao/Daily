package com.orange.module_base.utils

import com.alibaba.android.arouter.launcher.ARouter
import com.orange.module_base.base.BaseActivity
import com.orange.module_base.base.BaseFragment


object ARouterUtils {


    /**
     * 根据path返回Fragment
     *
     * @param path path
     * @return fragment
     */
    fun getFragment(path: String): BaseFragment? {
        var target=ARouter.getInstance()
                .build(path)
                .navigation()
        if (target==null){
            return null
        }
        return target as BaseFragment
    }

    /**
     * 根据path返回Activity
     *
     * @param path path
     * @return Activity
     */
    fun getActivity(path: String): BaseActivity? {
        var target=ARouter.getInstance()
                .build(path)
                .navigation()
        if (target==null){
            return null
        }
        return target as BaseActivity
    }
}

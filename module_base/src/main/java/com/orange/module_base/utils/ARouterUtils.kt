package com.orange.module_base.utils

import com.alibaba.android.arouter.launcher.ARouter
import com.orange.module_base.base.BaseActivity
import com.orange.module_base.base.BaseFragment

/**
 * Describe：ARouter帮助类
 * Created by 吴天强 on 2018/11/13.
 */

object ARouterUtils {


    /**
     * 根据path返回Fragment
     *
     * @param path path
     * @return fragment
     */
    fun getFragment(path: String): BaseFragment? {
        return ARouter.getInstance()
                .build(path)
                .navigation() as BaseFragment
    }

    /**
     * 根据path返回Activity
     *
     * @param path path
     * @return Activity
     */
    fun getActivity(path: String): BaseActivity? {
        return ARouter.getInstance()
                .build(path)
                .navigation() as BaseActivity
    }
}

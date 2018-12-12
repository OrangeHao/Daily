package com.orange.module_base.base

import android.os.Bundle


/**
 * 注意：setUserVisibleHint方法只有viewpage+fragment方式才有效
 *
 * created by czh on 2018/4/27
 */
abstract class BaseLazyFragment : BaseFragment() {

    protected var isViewInitiated: Boolean = false
    protected var isVisibleToUser: Boolean = false
    protected var isDataInitiated: Boolean = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isViewInitiated = true
        prepareFetchData()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        prepareFetchData()
    }

    abstract fun fetchData()

    @JvmOverloads
    fun prepareFetchData(forceUpdate: Boolean = false): Boolean {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            isDataInitiated = true
            fetchData()
            return true
        }
        return false
    }

}


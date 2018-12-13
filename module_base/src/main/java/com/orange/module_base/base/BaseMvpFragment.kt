package com.orange.module_base.base

import android.os.Bundle
import android.view.View

/**
 * created by czh on 2018/12/13
 */
abstract class BaseMvpFragment<V,T:BasePresenter<*>>: BaseFragment() {

    protected var mPresenter: T? = null
    protected fun createPresenter(): T? {
        return null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPresenter = createPresenter()
        if (mPresenter != null) {
            mPresenter!!.attachView(this as Nothing)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter!= null) {
            mPresenter!!.detachView()
            mPresenter!!.unsubscribe()
        }
    }
}
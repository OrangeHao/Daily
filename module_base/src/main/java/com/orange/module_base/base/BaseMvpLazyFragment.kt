package com.orange.module_base.base

import android.os.Bundle
import android.view.View

/**
 * created by czh on 2018/12/26
 */
abstract class BaseMvpLazyFragment<T:BasePresenter<*>>: BaseLazyFragment() {

    protected var mPresenter: T? = null
    open fun createPresenter(): T? {
        return null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPresenter = createPresenter()
        if (mPresenter != null) {
            mPresenter!!.attachView(this as BaseView)
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
package com.orange.module_base.base

import android.os.Bundle
import org.greenrobot.eventbus.EventBus

/**
 * created by czh on 2018/12/13
 */
abstract class BaseMvpActivity<V,T:BasePresenter<*>>: BaseActivity() {

    protected var mPresenter: T? = null
    protected fun createPresenter(): T? {
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
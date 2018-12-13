package com.orange.module_base.base

import android.content.Context


import java.lang.ref.Reference
import java.lang.ref.WeakReference

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Presenter基类  可用可不用
 * @param <V>
</V> */
abstract class BasePresenter<V:BaseView>(var mContext: Context) {

    protected var mViewRef: Reference<V>? = null
    protected var mCompositeDisposable: CompositeDisposable


    init {
        mCompositeDisposable = CompositeDisposable()
    }

    fun attachView(view: V) {
        mViewRef = WeakReference(view)
    }

    fun isViewAttached():Boolean=(mViewRef != null && mViewRef!!.get() != null)

    fun detachView() {
        if (mViewRef != null) {
            mViewRef!!.clear()
            mViewRef = null
        }
    }

    abstract fun subscribe()

    fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    fun addDisposable(disposable: Disposable) {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.add(disposable)
        }
    }

}
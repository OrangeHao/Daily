package com.orange.module_pictures.ui.jiandan.mvp

import android.content.Context

import com.orange.module_base.base.BasePresenter
import com.orange.module_pictures.model.JianDanPicturesBean
import com.orange.module_pictures.model.PicsBean
import com.orange.module_pictures.network.PicturesApiRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList


/**
 * created by czh on 2018/5/11
 */
class HandyPicturesPresenter(context: Context) : BasePresenter<HandyPicturesView>(context) {


    override fun subscribe() {

    }


    fun loadHandyPictures(pageIndex:Int){
        PicturesApiRepository.getInstance()!!.getHandyPics(pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<JianDanPicturesBean>> {
                    override fun onSuccess(t: List<JianDanPicturesBean>) {
                        if (pageIndex==1){
                            getView()!!.getRefreshDatas(getPicUrls(t))
                        }else{
                            getView()!!.getLoadMoreDatas(getPicUrls(t))
                        }
                    }

                    override fun onSubscribe(d: Disposable) {
                        addDisposable(d)
                    }

                    override fun onError(e: Throwable) {
                        getView()!!.onFailed(e)
                    }

                })
    }


    fun loadHandyHotPictures(){
        PicturesApiRepository.getInstance()!!.getHandyHotPics()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<JianDanPicturesBean>> {
                    override fun onSuccess(t: List<JianDanPicturesBean>) {
                        getView()!!.getRefreshDatas(getPicUrls(t))
                    }

                    override fun onSubscribe(d: Disposable) {
                        addDisposable(d)
                    }

                    override fun onError(e: Throwable) {
                        getView()!!.onFailed(e)
                    }

                })
    }


    private fun getPicUrls(data: List<JianDanPicturesBean>): List<String> {
        val temp = ArrayList<String>()
        for (bean in data) {
            if (bean.pics != null) {
                temp.addAll(bean.pics)
            }
        }
        return temp
    }



}

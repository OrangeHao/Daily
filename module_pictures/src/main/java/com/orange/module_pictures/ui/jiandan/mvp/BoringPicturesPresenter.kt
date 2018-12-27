package com.orange.module_pictures.ui.jiandan.mvp

import android.content.Context
import com.orange.module_base.base.BasePresenter
import com.orange.module_pictures.model.BoringHotPicsBean
import com.orange.module_pictures.model.BoringPicsBean
import com.orange.module_pictures.model.JianDanPicturesBean
import com.orange.module_pictures.network.PicturesApiRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

/**
 * created by czh on 2018/12/27
 */
class BoringPicturesPresenter(context: Context) : BasePresenter<BoringPicturesView>(context) {


    override fun subscribe() {

    }


    fun loadBoringPictures(pageIndex:Int){
        PicturesApiRepository.getInstance()!!.getBoringPics(pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<BoringPicsBean>> {
                    override fun onSuccess(t: List<BoringPicsBean>) {
                        if (pageIndex==1){
                            getView()!!.getRefreshDatas(t)
                        }else{
                            getView()!!.getLoadMoreDatas(t)
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


    fun loadBoringHotPictures(){
        PicturesApiRepository.getInstance()!!.getBoringHotPics()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<BoringHotPicsBean>> {
                    override fun onSuccess(t: List<BoringHotPicsBean>) {
                        getView()!!.getHotDatas(t)
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
package com.orange.module_pictures.ui.jiandan.mvp

import android.content.Context
import android.util.Log
import com.orange.module_base.base.BasePresenter
import com.orange.module_collector.beans.MediaBean
import com.orange.module_collector.file.FileSavePlaces
import com.orange.module_collector.ui.picture.adapter.MediaListsAdapter.Companion.TYPE_DATE_TITLE
import com.orange.module_collector.utils.CalendarUtils
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * created by czh on 2018/12/27
 */
class MediaMainListPresenter(val context: Context) : BasePresenter<MediaMainListView>(context) {


    override fun subscribe() {

    }

    fun getAllDataUnderFolder(){
        Single.create(object:SingleOnSubscribe<List<MediaBean>>{
            override fun subscribe(p0: SingleEmitter<List<MediaBean>>) {
                p0.onSuccess(addDateTitleToDataList(FileSavePlaces.getAllMediaData(context)))
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<MediaBean>> {
                    override fun onSuccess(t: List<MediaBean>) {
                        Log.d("czh","total size:${t.size}")
                        getView()?.receiveData(t)
                    }

                    override fun onSubscribe(d: Disposable) {
                        addDisposable(d)
                    }

                    override fun onError(e: Throwable) {
                        getView()!!.onFailed(e)
                    }

                })
    }


    fun addDateTitleToDataList(list: List<MediaBean>): List<MediaBean>{
        val tempList= mutableListOf<MediaBean>()
        for((index,item) in list.withIndex()){
            if (index==0 || !CalendarUtils.isOneDay(item.createTime,list.get(index-1).createTime)){
                val mediaBean=MediaBean(createTime = item.createTime)
                mediaBean.setBeanType(TYPE_DATE_TITLE)
//                tempList.add(mediaBean)
                tempList.add(item)
            }else{
                tempList.add(item)
            }
        }
        return tempList
    }



}
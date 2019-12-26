package com.orange.module_pictures.ui.jiandan.mvp

import android.content.Context
import android.util.Log
import com.orange.module_base.base.BasePresenter
import com.orange.module_collector.beans.MediaBean
import com.orange.module_collector.beans.MediaSection
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
        Single.create(object:SingleOnSubscribe<List<MediaSection>>{
            override fun subscribe(p0: SingleEmitter<List<MediaSection>>) {
                p0.onSuccess(addDateTitleToDataList(FileSavePlaces.getAllMediaData(context)))
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<MediaSection>> {
                    override fun onSuccess(t: List<MediaSection>) {
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


    fun addDateTitleToDataList(list: List<MediaBean>): List<MediaSection>{
        val tempList= mutableListOf<MediaSection>()
        for((index,item) in list.withIndex()){
            if (index==0 || !CalendarUtils.isOneDay(item.createTime,list.get(index-1).createTime)){
                val head=MediaSection(true,CalendarUtils.getDateAndDayOfWeek(item.createTime))
                tempList.add(head)
            }
            tempList.add(MediaSection(item))
        }
        return tempList
    }



}
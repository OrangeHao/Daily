package com.orange.module_pictures.ui.jiandan.mvp

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.orange.module_base.base.BasePresenter
import com.orange.module_collector.beans.MediaBean
import com.orange.module_collector.beans.MediaSection
import com.orange.module_collector.file.FileSavePlaces
import com.orange.module_collector.utils.CalendarUtils
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.SingleObserver
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.File


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


    fun sharePictures(list:List<MediaSection>){
        if (list.size==0){
            Toast.makeText(context,"Please select pictures to share!",Toast.LENGTH_SHORT).show()
            return
        }
        val imgUris = ArrayList<Uri>()
        for (item in list){
            Log.d("czh","share path:"+item.t.path)
            imgUris.add(Uri.fromFile(File(item.t.path)))
        }

        var shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND_MULTIPLE
        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imgUris)
        shareIntent.type = "image/*"
        //切记需要使用Intent.createChooser，否则会出现别样的应用选择框，您可以试试
        shareIntent = Intent.createChooser(shareIntent, "Here is the title of Select box")
        context.startActivity(shareIntent)
    }


    fun getImageContentUri(context: Context, filePath: String): Uri? {
        val cursor: Cursor? = context.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                arrayOf(MediaStore.Images.Media._ID), MediaStore.Images.Media.DATA + "=? ",
                arrayOf(filePath), null)
        if (cursor != null && cursor.moveToFirst()) {
            val id: Int = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID))
            val baseUri = Uri.parse("content://media/external/images/media")
            return Uri.withAppendedPath(baseUri, "" + id)
        } else {
            return null
        }
    }

}
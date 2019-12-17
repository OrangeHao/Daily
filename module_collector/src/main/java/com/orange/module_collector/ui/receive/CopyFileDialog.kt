package com.orange.module_collector.ui.receive

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import com.lxj.xpopup.core.CenterPopupView
import com.orange.module_collector.R
import com.orange.module_collector.beans.FolderBean
import com.orange.module_collector.common.PNG_POSTFIX
import com.orange.module_collector.file.FileHelper
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.File


/**
 * created by czh on 2019/12/1
 */
class CopyFileDialog(context: Context):CenterPopupView(context) {

    override fun getImplLayoutId(): Int {
        return R.layout.module_collector_copy_dialog
    }


    private var mReceiveData=ArrayList<Uri>()
    fun setCopyData(data:ArrayList<Uri>){
        mReceiveData.addAll(data)
    }


    private var mIsDelete=false
    private var mTargetFolderBean:FolderBean?=null
    fun setTarget(folderBean: FolderBean){
        mTargetFolderBean=folderBean
    }


    interface CopyFileListener{
        fun complete()
    }
    private var mListener:CopyFileListener?=null
    fun setCopyListener(copyFileListener: CopyFileListener){
        mListener=copyFileListener
    }


    private var mProgressTextView:TextView?=null
    override fun onCreate() {
        super.onCreate()
        findViewById<TextView>(R.id.tv_title).setText(context.getString(R.string.module_collector_copy_tips,mTargetFolderBean?.name))
        mProgressTextView=findViewById(R.id.tv_progress)

        findViewById<CheckBox>(R.id.checkBox).setOnCheckedChangeListener { buttonView, isChecked ->
            mIsDelete=isChecked
        }
        findViewById<View>(R.id.tv_confirm).setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
               startCopyFile()
            }
        })
        findViewById<View>(R.id.tv_cancel).setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                dismiss()
            }
        })
    }

    private fun startCopyFile(){
        if (mReceiveData.size==0){
            return
        }

        findViewById<LinearLayout>(R.id.layout_confirm).visibility=View.GONE
        findViewById<CheckBox>(R.id.checkBox).visibility=View.GONE
        findViewById<RelativeLayout>(R.id.progress_layout).visibility=View.VISIBLE

        Observable.create(object:ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                var count=0
                for (item in mReceiveData){
                    Log.d("czh","uri:${item.toString()}")
                    FileHelper.copyFileFromUri(context,item,getTargetPath(item))
                    if (mIsDelete){
//                        val delete=context.contentResolver.delete(item,null,null)
                        deleteFileByUri(context,item)
                    }
                    count++
                    emitter.onNext(count)
                }
                emitter.onComplete()
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<Int>{
                    override fun onComplete() {
                        Toast.makeText(context,"收藏成功",Toast.LENGTH_SHORT).show()
                        mListener?.complete()
                        dismiss()
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(progress: Int) {
                        mProgressTextView?.setText("$progress/${mReceiveData.size}")
                    }

                    override fun onError(e: Throwable) {

                    }
                })
    }

    private fun getTargetPath(uri: Uri):String{
        val name=System.currentTimeMillis().toString()+ PNG_POSTFIX
        val file=File(mTargetFolderBean?.path,name)
        val path=file.absolutePath
        Log.d("czh","copy to path:"+path)
        return path
    }


    private fun deleteFileByUri(context: Context,uri: Uri){
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cur: Cursor = MediaStore.Images.Media.query(context.contentResolver, uri, projection, null, null)
        cur.moveToFirst()
        val path: String = cur.getString(cur.getColumnIndex(MediaStore.Images.Media.DATA))
        val file = File(path)
        Log.d("czh","path from uri:"+file.absolutePath)
        if(file.exists()){
            file.delete()
            updateFileFromDatabase(context,file.absolutePath)
        }
        cur.close()
    }

    private fun updateFileFromDatabase(context: Context,filepath:String){
        val where=MediaStore.Audio.Media.DATA+" like \""+filepath+"%"+"\""
        val i=context.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,where,null)
        if(i>0){

        }
    }


}
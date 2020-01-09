package com.orange.module_collector.ui.picture

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.View
import android.widget.*
import com.lxj.xpopup.core.CenterPopupView
import com.orange.module_collector.R
import com.orange.module_collector.beans.MediaSection
import com.orange.module_collector.ui.picture.adapter.MediaListsAdapter
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.File

/**
 * created by czh on 2019/12/30
 */
class DeleteFileDialog(context: Context): CenterPopupView(context) {

    override fun getImplLayoutId(): Int {
        return R.layout.module_collector_delete_dialog
    }


    private val mDataList = ArrayList<MediaSection>()
    private var mAdapter: MediaListsAdapter? = null
    private var mRecyclerView:RecyclerView?=null
    fun setDeleteFiles(data:ArrayList<MediaSection>){
        mDataList.addAll(data)
    }


    interface DeleteFileListener{
        fun complete()
    }
    private var mListener:DeleteFileListener?=null
    fun setDeleteListener(deleteFileListener: DeleteFileListener){
        mListener=deleteFileListener
    }


    private var mProgressTextView: TextView?=null
    override fun onCreate() {
        super.onCreate()
        findViewById<TextView>(R.id.tv_title).setText(context.getString(R.string.module_collector_delete_tips,mDataList.size))
        mProgressTextView=findViewById(R.id.tv_progress)

        showDeleteList()

        findViewById<View>(R.id.tv_confirm).setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                startDeleteFile()
            }
        })
        findViewById<View>(R.id.tv_cancel).setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                dismiss()
            }
        })
    }


    private fun showDeleteList(){
        Log.d("czh","size:"+mDataList.size)
        mAdapter=MediaListsAdapter(mDataList)
        mRecyclerView=findViewById(R.id.recyclerView)
        val layoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy= StaggeredGridLayoutManager.GAP_HANDLING_NONE
        mRecyclerView?.itemAnimator=null
        mRecyclerView?.setLayoutManager(layoutManager)
        mRecyclerView?.setAdapter(mAdapter)
    }

    private fun startDeleteFile(){
        if (mDataList.size==0){
            return
        }

        findViewById<LinearLayout>(R.id.layout_confirm).visibility= View.GONE
        mRecyclerView?.visibility= View.GONE
        findViewById<RelativeLayout>(R.id.progress_layout).visibility= View.VISIBLE

        Observable.create(object: ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                var count=0
                for (item in mDataList){
                    Log.d("czh","delete file:${item.t.path}")
                    val file=File(item.t.path)
                    if (file.exists()){
                        file.delete()
                    }
                    count++
                    emitter.onNext(count)
                }
                emitter.onComplete()
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Int> {
                    override fun onComplete() {
                        Toast.makeText(context,"Delete  ${mDataList.size} files succeed", Toast.LENGTH_SHORT).show()
                        mListener?.complete()
                        dismiss()
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(progress: Int) {
                        mProgressTextView?.setText("$progress/${mDataList.size}")
                    }

                    override fun onError(e: Throwable) {

                    }
                })
    }


}
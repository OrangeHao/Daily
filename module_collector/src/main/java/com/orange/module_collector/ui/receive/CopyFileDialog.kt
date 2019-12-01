package com.orange.module_collector.ui.receive

import com.lxj.xpopup.core.CenterPopupView
import com.lxj.xpopup.animator.PopupAnimator
import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.orange.module_collector.R


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

    private var mTitle=""
    fun setTitle(title:String){
        mTitle=title
    }

    private var mProgressTextView:TextView?=null
    override fun onCreate() {
        super.onCreate()
        findViewById<TextView>(R.id.tv_title).setText(mTitle)
        mProgressTextView=findViewById(R.id.tv_progress)
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
        findViewById<LinearLayout>(R.id.layout_confirm).visibility=View.GONE
        findViewById<LinearLayout>(R.id.progress_layout).visibility=View.VISIBLE


    }
}
package com.orange.module_collector.ui

import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import com.lxj.xpopup.XPopup
import com.orange.module_base.base.BaseActivity
import com.orange.module_base.utils.checkPermissionWriteExteralStorage
import com.orange.module_base.utils.dp2px
import com.orange.module_collector.R
import com.orange.module_collector.beans.FolderBean
import com.orange.module_collector.file.FileSavePlaces
import com.orange.module_collector.ui.adapter.FolderListAdapter
import com.orange.module_collector.utils.DividerGridItemDecoration
import kotlinx.android.synthetic.main.module_collector_activity_receiver.*


class ReceiverActivity : BaseActivity() {
    override fun getContentLayoutId(): Int = R.layout.module_collector_activity_receiver


    val mFolderList=ArrayList<FolderBean>()
    var mAdapter:FolderListAdapter?=null


    override fun initView() {

       val files= getExternalFilesDirs("")
        for (item in files){
            Log.d("czh",item.absolutePath)
        }

        checkPermissionWriteExteralStorage {
            if (!it){
                finish()
            }
        }


        mAdapter = FolderListAdapter(mFolderList)
        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy= StaggeredGridLayoutManager.GAP_HANDLING_NONE
        recyclerview.itemAnimator=null
        recyclerview.setLayoutManager(layoutManager)
        recyclerview.addItemDecoration(DividerGridItemDecoration(3, dp2px( 6), false))
        recyclerview.setAdapter(mAdapter)

        swipeLayout.setOnRefreshListener {
            getFolderList()
        }

        mAdapter?.setOnItemClickListener { adapter, view, position ->
            if (position==mFolderList.size-1){
                createNewFolder()
            }
        }
    }


    override fun initData() {
        getFolderList()
    }



    private fun startCopyFiles(){

    }


    private fun createNewFolder(){
        XPopup.Builder(this).asInputConfirm("创建新收藏集", "请输入名称") { text ->
            FileSavePlaces.createNewCollection(this,text)
            getFolderList()
        }.show()
    }


    private fun getFolderList(){
        mFolderList.clear()
        mFolderList.addAll(FileSavePlaces.getFolderList(this))
        //add item
        mFolderList.add(FolderBean())
        mAdapter?.notifyDataSetChanged()
        swipeLayout.isRefreshing=false
    }



}

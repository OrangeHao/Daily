package com.orange.module_collector.ui.receive

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.StaggeredGridLayoutManager
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


class ReceiverActivity : BaseActivity() ,CopyFileDialog.CopyFileListener{
    override fun getContentLayoutId(): Int = R.layout.module_collector_activity_receiver


    val mFolderList = ArrayList<FolderBean>()
    var mAdapter: FolderListAdapter? = null

    var mReceiveData = ArrayList<Uri>()


    override fun initView() {


        checkPermissionWriteExteralStorage {
            if (!it) {
                finish()
            }
        }


        initRecyclerView()
    }

    fun initRecyclerView() {
        mAdapter = FolderListAdapter(mFolderList)
        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        recyclerview.itemAnimator = null
        recyclerview.setLayoutManager(layoutManager)
        recyclerview.addItemDecoration(DividerGridItemDecoration(3, dp2px(6), false))
        recyclerview.setAdapter(mAdapter)

        swipeLayout.setOnRefreshListener {
            getFolderList()
        }

        mAdapter?.setOnItemClickListener { adapter, view, position ->
            if (position == mFolderList.size - 1) {
                createNewFolder()
            } else {
                startCopyFiles(mFolderList.get(position))
            }
        }
    }


    override fun initData() {
        getFolderList()
        getData()
    }


    private fun getData() {
        val intent = intent
        val action = intent.action
        val type = intent.type

        if (Intent.ACTION_SEND == action && type != null) {
            if (type.startsWith("image/")) {
                val imageUri = intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
                if (imageUri != null) {
                    mReceiveData.add(imageUri)
                }
            }
        } else if (Intent.ACTION_SEND_MULTIPLE == action && type != null) {
            if (type.startsWith("image/")) {
                val imageUris = intent.getParcelableArrayListExtra<Uri>(Intent.EXTRA_STREAM)
                val stringBuilder = StringBuilder()
                if (imageUris != null) {
                    for (item in imageUris) {
                        mReceiveData.add(item)
                        stringBuilder.append(item.path).append("\n")
                    }
                }
            }
        }
    }


    private fun startCopyFiles(folder: FolderBean) {
        val dialog = CopyFileDialog(this).apply {
            setCopyData(mReceiveData)
            setTarget(folder)
        }
        XPopup.Builder(this)
                .dismissOnTouchOutside(false)
                .asCustom(dialog)
                .show()
    }


    private fun createNewFolder() {
        XPopup.Builder(this).asInputConfirm("创建新收藏集", "请输入名称") { text ->
            FileSavePlaces.createNewCollection(this, text)
            getFolderList()
        }.show()
    }


    private fun getFolderList() {
        mFolderList.clear()
        mFolderList.addAll(FileSavePlaces.getFolderList(this))
        //add item
        mFolderList.add(FolderBean())
        mAdapter?.notifyDataSetChanged()
        swipeLayout.isRefreshing = false
    }

    override fun complete() {

    }


}

package com.orange.module_collector.ui.receive

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.StaggeredGridLayoutManager
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.interfaces.OnCancelListener
import com.lxj.xpopup.interfaces.OnConfirmListener
import com.orange.module_base.base.BaseActivity
import com.orange.module_base.utils.checkPermissionWriteExteralStorage
import com.orange.module_base.utils.dp2px
import com.orange.module_collector.R
import com.orange.module_collector.beans.FolderBean
import com.orange.module_collector.beans.ReceiverBean
import com.orange.module_collector.file.FileSavePlaces
import com.orange.module_collector.ui.adapter.FolderListAdapter
import com.orange.module_collector.ui.main.ModuleCollectorMainActivity
import com.orange.module_collector.ui.picture.adapter.MediaListsAdapter
import com.orange.module_collector.utils.DividerGridItemDecoration
import kotlinx.android.synthetic.main.module_collector_activity_receiver.*


class ReceiverActivity : BaseActivity(), CopyFileDialog.CopyFileListener {
    override fun getContentLayoutId(): Int = R.layout.module_collector_activity_receiver


    val mFolderList = ArrayList<FolderBean>()
    var mAdapter: FolderListAdapter? = null

    var mReceiveData = ArrayList<ReceiverBean>()



    override fun initView() {
        checkPermissionWriteExteralStorage {
            if (!it) {
                finish()
            }
        }

        initNormalTitleBar("接收文件")
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
            val uri = intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
            if (uri != null) {
                if (type.startsWith("image/")) {
                    mReceiveData.add(ReceiverBean(uri,MediaListsAdapter.TYPE_PIC))
                }else if (type.startsWith("video/")){
                    mReceiveData.add(ReceiverBean(uri,MediaListsAdapter.TYPE_VIDEO))
                }
            }
        } else if (Intent.ACTION_SEND_MULTIPLE == action && type != null) {
            val uris = intent.getParcelableArrayListExtra<Uri>(Intent.EXTRA_STREAM)
            val stringBuilder = StringBuilder()
            if (uris != null) {
                for (item in uris) {
                    if (type.startsWith("image/")) {
                        mReceiveData.add(ReceiverBean(item,MediaListsAdapter.TYPE_PIC))
                    }else if (type.startsWith("video/")){
                        mReceiveData.add(ReceiverBean(item,MediaListsAdapter.TYPE_VIDEO))
                    }
                    stringBuilder.append(item.path).append("\n")
                }
            }
        }
    }


    private fun startCopyFiles(folder: FolderBean) {
        val dialog = CopyFileDialog(this).apply {
            setCopyData(mReceiveData)
            setTarget(folder)
            setCopyListener(this@ReceiverActivity)
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
        XPopup.Builder(this).asConfirm("保存成功", "", "返回第三方工具", "打开主页", object : OnConfirmListener {
            override fun onConfirm() {
                finish()
                startActivity(Intent(this@ReceiverActivity, ModuleCollectorMainActivity::class.java))
            }
        }, object : OnCancelListener {
            override fun onCancel() {
                finish()
            }
        }, false).show()
    }


}

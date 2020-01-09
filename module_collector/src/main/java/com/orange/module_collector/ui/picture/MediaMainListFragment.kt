package com.orange.module_collector.ui.picture


import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.lxj.xpopup.XPopup
import com.orange.module_base.base.BaseMvpLazyFragment
import com.orange.module_collector.R
import com.orange.module_collector.beans.MediaBean
import com.orange.module_collector.beans.MediaSection
import com.orange.module_collector.ui.main.ModuleCollectorMainActivity
import com.orange.module_collector.ui.picture.adapter.MediaListsAdapter
import com.orange.module_pictures.ui.jiandan.mvp.MediaMainListPresenter
import com.orange.module_pictures.ui.jiandan.mvp.MediaMainListView
import kotlinx.android.synthetic.main.module_collector_fragment_picture_list.*


/**
 */
class MediaMainListFragment : BaseMvpLazyFragment<MediaMainListPresenter>(), MediaMainListView,ModuleCollectorMainActivity.MenuActionListener {
    override fun getLayoutId(): Int = R.layout.module_collector_fragment_picture_list

    private val mDataList = ArrayList<MediaSection>()
    private var mAdapter: MediaListsAdapter? = null

    companion object {
        @JvmStatic
        fun newInstance() =
                MediaMainListFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }


    override fun initView() {
        super.initView()
        mAdapter = MediaListsAdapter(mDataList)
        val layoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy= StaggeredGridLayoutManager.GAP_HANDLING_NONE
        recyclerview.itemAnimator=null
        recyclerview.setLayoutManager(layoutManager)
//        recyclerview.addItemDecoration(DividerGridItemDecoration(4, context!!.dp2px( 6), false))
        val defaultItemAnimator = DefaultItemAnimator()
        recyclerview.setItemAnimator(defaultItemAnimator)
        recyclerview.setAdapter(mAdapter)

        swipeLayout.setColorSchemeResources(R.color.colorPrimary)
        swipeLayout.setOnRefreshListener {
            mPresenter?.getAllDataUnderFolder()
        }

        mAdapter?.setOnItemLongClickListener (object :BaseQuickAdapter.OnItemLongClickListener{
            override fun onItemLongClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int): Boolean {
                mAdapter?.changeViewMode()
                return true
            }
        })

        mAdapter?.setOnItemClickListener { adapter, view, position ->
            if (mAdapter!!.mMarkMode){
                mAdapter?.markItem(view,position)
            }else{
                goToViewBigPicture(position)
            }
        }
    }



    private fun goToViewBigPicture(position:Int){
        val list=ArrayList<MediaBean>()
        var realIndex=position
        for ((index,item) in mDataList.withIndex()){
            if (item.isHeader){
                if (index<=position){
                    realIndex--
                }
            }else{
                list.add(item.t)
            }
        }
        PictureViewActivity.start(context!!,list,realIndex)
    }

    override fun fetchData() {

    }


    override fun createPresenter(): MediaMainListPresenter? {
        return MediaMainListPresenter(context!!)
    }

    override fun receiveData(data: List<MediaSection>) {
        swipeLayout.isRefreshing=false
        mDataList.clear()
        mDataList.addAll(data)
        mAdapter?.notifyDataSetChanged()
    }


    override fun onFailed(e: Throwable) {
        swipeLayout.isRefreshing=false
    }


    override fun actionById(actionId:Int){
        when(actionId){
            R.id.action_share->{
                if (mAdapter!!.mMarkMode){
                    mPresenter?.sharePictures(mAdapter!!.mMarkCollection)
                }
            }
            R.id.action_delete->{
                if (mAdapter!!.mMarkMode){
                   startDeleteFiles(mAdapter!!.mMarkCollection)
                }
            }
        }
    }


    private fun startDeleteFiles(data: List<MediaSection>) {
        val dialog = DeleteFileDialog(context!!).apply {
            setDeleteFiles(data as ArrayList<MediaSection>)
            setDeleteListener(object :DeleteFileDialog.DeleteFileListener{
                override fun complete() {
                    if (mAdapter!=null){
                        swipeLayout.isRefreshing
                        mPresenter?.getAllDataUnderFolder()
                    }
                }
            })
        }
        XPopup.Builder(context)
                .dismissOnTouchOutside(false)
                .asCustom(dialog)
                .show()
    }

}
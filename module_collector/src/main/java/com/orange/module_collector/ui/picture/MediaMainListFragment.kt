package com.orange.module_collector.ui.picture


import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.orange.module_base.base.BaseMvpLazyFragment
import com.orange.module_base.utils.dp2px

import com.orange.module_collector.R
import com.orange.module_collector.beans.MediaBean
import com.orange.module_collector.ui.picture.adapter.MediaListsAdapter
import com.orange.module_collector.utils.DividerGridItemDecoration
import com.orange.module_pictures.ui.jiandan.mvp.MediaMainListPresenter
import com.orange.module_pictures.ui.jiandan.mvp.MediaMainListView
import kotlinx.android.synthetic.main.module_collector_fragment_picture_list.*

/**
 */
class MediaMainListFragment : BaseMvpLazyFragment<MediaMainListPresenter>(), MediaMainListView {
    override fun getLayoutId(): Int = R.layout.module_collector_fragment_picture_list

    private val mDataList = ArrayList<MediaBean>()
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
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy= StaggeredGridLayoutManager.GAP_HANDLING_NONE
        recyclerview.itemAnimator=null
        recyclerview.setLayoutManager(layoutManager)
        recyclerview.addItemDecoration(DividerGridItemDecoration(2, context!!.dp2px( 6), false))
        recyclerview.setAdapter(mAdapter)

        swipeLayout.setColorSchemeResources(R.color.colorPrimary)
        swipeLayout.setOnRefreshListener {
            mPresenter?.getAllDataUnderFolder()
        }


        mAdapter?.setOnItemClickListener { adapter, view, position ->
            PictureViewActivity.start(context!!,mDataList,position)
        }
    }

    override fun fetchData() {

    }


    override fun createPresenter(): MediaMainListPresenter? {
        return MediaMainListPresenter(context!!)
    }

    override fun receiveData(data: List<MediaBean>) {
        swipeLayout.isRefreshing=false
        mDataList.clear()
        mDataList.addAll(data)
        mAdapter?.notifyDataSetChanged()
    }


    override fun onFailed(e: Throwable) {
        swipeLayout.isRefreshing=false
    }


}
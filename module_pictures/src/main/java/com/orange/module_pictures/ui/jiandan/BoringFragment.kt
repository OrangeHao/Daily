package com.orange.module_pictures.ui.jiandan


import android.os.Bundle
import com.chad.library.adapter.base.BaseQuickAdapter
import com.orange.module_base.base.BaseMvpLazyFragment
import com.orange.module_base.utils.dp2px

import com.orange.module_pictures.model.BoringHotPicsBean
import com.orange.module_pictures.model.BoringPicsBean
import com.orange.module_pictures.ui.jiandan.adapter.BoringPicsAdapter
import com.orange.module_pictures.ui.jiandan.mvp.BoringPicturesPresenter
import com.orange.module_pictures.ui.jiandan.mvp.BoringPicturesView
import com.orange.module_pictures.utils.DividerGridItemDecoration
import kotlinx.android.synthetic.main.module_pictures_fragment_boring.*
import android.support.v7.widget.StaggeredGridLayoutManager
import android.R




/**
 * @author OrangeHao
 * @date 2018/12/26
 * @Github https://github.com/OrangeHao
 * @describe
 */

class BoringFragment : BaseMvpLazyFragment<BoringPicturesPresenter>(), BoringPicturesView {
    override fun getLayoutId(): Int = com.orange.module_pictures.R.layout.module_pictures_fragment_boring

    private val mDataList = ArrayList<BoringPicsBean>()
    private var mAdapter: BoringPicsAdapter? = null

    private var mPageIndex=1

    companion object {
        @JvmStatic
        fun newInstance() =
                BoringFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }


    override fun initView() {
        super.initView()
        mAdapter = BoringPicsAdapter(mDataList)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy=StaggeredGridLayoutManager.GAP_HANDLING_NONE
        recyclerview.itemAnimator=null
        recyclerview.setLayoutManager(layoutManager)
        recyclerview.addItemDecoration(DividerGridItemDecoration(2, context!!.dp2px( 6), false))
        recyclerview.setAdapter(mAdapter)

        swipeLayout.setColorSchemeResources(com.orange.module_pictures.R.color.colorPrimary)
        swipeLayout.setOnRefreshListener {
            mPageIndex=1
            mPresenter!!.loadBoringPictures(mPageIndex)
        }
        mAdapter!!.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener{
            override fun onLoadMoreRequested() {
                mPageIndex++
                mPresenter!!.loadBoringPictures(mPageIndex)
            }
        },recyclerview)

        mAdapter?.setOnItemClickListener { adapter, view, position ->
            PhotoViewActivity.start(context!!,getAllPictures(),position)
        }
    }

    override fun fetchData() {
//        swipeLayout.isRefreshing=true
//        mPresenter!!.loadBoringPictures(mPageIndex)
    }


    override fun createPresenter(): BoringPicturesPresenter? {
        return BoringPicturesPresenter(context!!)
    }


    override fun onFailed(e: Throwable) {
        swipeLayout.isRefreshing=false
        mAdapter!!.loadMoreComplete()
    }

    override fun getRefreshDatas(data: List<BoringPicsBean>) {
        swipeLayout.isRefreshing=false
        mDataList.clear()
        mDataList.addAll(data)
        mAdapter!!.notifyDataSetChanged()
    }

    override fun getLoadMoreDatas(data: List<BoringPicsBean>) {
        mDataList.addAll(data)
        mAdapter!!.notifyDataSetChanged()
        mAdapter!!.loadMoreComplete()
    }

    override fun getHotDatas(data: List<BoringHotPicsBean>) {

    }

    private fun getAllPictures():ArrayList<String>{
        val tempList=ArrayList<String>()
        for (item in mDataList){
            tempList.addAll(item.pics)
        }
        return tempList
    }
}
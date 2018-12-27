package com.orange.module_pictures.ui.jiandan

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.orange.module_base.base.BaseMvpLazyFragment
import com.orange.module_base.utils.dp2px
import com.orange.module_pictures.R
import com.orange.module_pictures.ui.jiandan.adapter.JianDanPicsListAdapter

import com.orange.module_pictures.ui.jiandan.mvp.HandyPicturesPresenter
import com.orange.module_pictures.ui.jiandan.mvp.HandyPicturesView
import com.orange.module_pictures.utils.DividerGridItemDecoration
import kotlinx.android.synthetic.main.module_pictures_fragment_handy.*


/**
 * @author OrangeHao
 * @date 2018/12/26
 * @Github https://github.com/OrangeHao
 * @describe
 */

private const val IS_HOT = "IS_HOT"

class HandyFragment : BaseMvpLazyFragment<HandyPicturesPresenter>(), HandyPicturesView {


    override fun getLayoutId(): Int = R.layout.module_pictures_fragment_handy

    private val mDataList = ArrayList<String>()
    private var mAdapter: JianDanPicsListAdapter? = null
    private var isHot=false

    private var mPageIndex=1

    companion object {
        @JvmStatic
        fun newInstance(isHot:Boolean) =
                HandyFragment().apply {
                    arguments = Bundle().apply {
                        putBoolean(IS_HOT,isHot)
                    }
                }
    }


    override fun initView() {
        super.initView()
        isHot= arguments?.getBoolean(IS_HOT)!!
        mAdapter = JianDanPicsListAdapter(mDataList)
        recyclerview.setLayoutManager(GridLayoutManager(context, 2))
        recyclerview.addItemDecoration(DividerGridItemDecoration(2, context!!.dp2px( 6), false))
        recyclerview.setAdapter(mAdapter)

        swipeLayout.setColorSchemeResources(R.color.colorPrimary)
        swipeLayout.setOnRefreshListener {
            if (isHot){
                mPresenter!!.loadHandyHotPictures()
            }else{
                mPageIndex=1
                mPresenter!!.loadHandyPictures(mPageIndex)
            }
        }
        if (!isHot){
            mAdapter!!.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener{
                override fun onLoadMoreRequested() {
                    mPageIndex++
                    mPresenter!!.loadHandyPictures(mPageIndex)
                }
            },recyclerview)
        }
    }

    override fun fetchData() {
        swipeLayout.isRefreshing=true
        if (isHot){
            mPresenter!!.loadHandyHotPictures()
        }else{
            mPresenter!!.loadHandyPictures(mPageIndex)
        }
    }


    override fun createPresenter(): HandyPicturesPresenter? {
        return HandyPicturesPresenter(context!!)
    }


    override fun getRefreshDatas(data: List<String>) {
        swipeLayout.isRefreshing=false
        mDataList.clear()
        mDataList.addAll(data)
        mAdapter!!.notifyDataSetChanged()
    }

    override fun getLoadMoreDatas(data: List<String>) {
        mDataList.addAll(data)
        mAdapter!!.notifyDataSetChanged()
        mAdapter!!.loadMoreComplete()
    }

    override fun onFailed(e: Throwable) {
        swipeLayout.isRefreshing=false
        mAdapter!!.loadMoreComplete()
    }
}
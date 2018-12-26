package com.orange.module_pictures.ui.jiandan


import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.orange.module_base.base.BaseLazyFragment
import com.orange.module_base.base.BaseMvpLazyFragment
import com.orange.module_base.utils.dp2px

import com.orange.module_pictures.R
import com.orange.module_pictures.ui.jiandan.mvp.HandyPicturesPresenter
import com.orange.module_pictures.ui.jiandan.mvp.HandyPicturesView
import com.orange.module_pictures.utils.DividerGridItemDecoration
import kotlinx.android.synthetic.main.module_pictures_fragment_handy_hot.*
import java.util.ArrayList


/**
 * @author OrangeHao
 * @date 2018/12/26
 * @Github https://github.com/OrangeHao
 * @describe
 */
class HandyHotFragment : BaseMvpLazyFragment<HandyPicturesPresenter>(), HandyPicturesView {


    override fun getLayoutId(): Int = R.layout.module_pictures_fragment_handy_hot

    private val mDataList = ArrayList<String>()
    private var mAdapter: JianDanPicsListAdapter? = null

    companion object {
        @JvmStatic
        fun newInstance() =
                HandyHotFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }


    override fun initView() {
        super.initView()
        mAdapter = JianDanPicsListAdapter(mDataList)
        recyclerview.setLayoutManager(GridLayoutManager(context, 2))
        recyclerview.addItemDecoration(DividerGridItemDecoration(2, context!!.dp2px( 6), false))
        recyclerview.setAdapter(mAdapter)

        swipeLayout.setColorSchemeResources(R.color.colorPrimary)
        swipeLayout.setOnRefreshListener {
            mPresenter!!.loadHandyHotPictures()
        }
    }

    override fun fetchData() {
        swipeLayout.isRefreshing=true
        mPresenter!!.loadHandyHotPictures()
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
        swipeLayout.isRefreshing=false
        mDataList.addAll(data)
        mAdapter!!.notifyDataSetChanged()
    }

    override fun onFailed(e: Throwable) {
        swipeLayout.isRefreshing=false
    }
}

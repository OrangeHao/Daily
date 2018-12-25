package com.orange.module_news.ui.cnbeta

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.chad.library.adapter.base.BaseQuickAdapter
import com.orange.module_base.base.BaseLazyFragment
import com.orange.module_news.R
import com.orange.module_news.model.CnBetaNewsItem
import com.orange.module_news.model.Post
import com.orange.module_news.network.NewsApiRepository
import com.orange.module_news.ui.jiandan.JianDanNewsDetailActivity
import com.orange.module_news.ui.jiandan.NewsJianDanListAdapter
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.module_news_fragment_news_cn_beta.*


class NewsCnBetaFragment : BaseLazyFragment() {
    override fun getLayoutId(): Int = R.layout.module_news_fragment_news_cn_beta

    companion object {
        @JvmStatic
        fun newInstance() =
                NewsCnBetaFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }


    var mAdapter: NewsCnBetaListAdapter? = null
    val mDataList= ArrayList<CnBetaNewsItem>()

    var mPageIndex=1


    override fun initView() {
        super.initView()

        mAdapter= NewsCnBetaListAdapter(mDataList)
        recyclerview.addItemDecoration(DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerview.layoutManager= LinearLayoutManager(context)
        recyclerview.adapter=mAdapter

        mAdapter!!.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener{
            override fun onLoadMoreRequested() {
                mPageIndex++
                var endSid=Int.MAX_VALUE
                if (mAdapter!!.data.size>0){
                    endSid=mAdapter!!.data.get(mAdapter!!.data.size-1).sid.toInt()
                }
                getNewsList(endSid)
            }
        },recyclerview)

        mAdapter!!.setOnItemClickListener { adapter, view, position ->
            CnbetaNewsDetailActivity.start(context!!,mAdapter!!.data.get(position).sid)
        }

        swipeLayout.setColorSchemeResources(R.color.colorPrimary)
        swipeLayout.setOnRefreshListener {
            mPageIndex=1
            getNewsList(Int.MAX_VALUE)
        }
    }

    override fun fetchData() {
        swipeLayout.isRefreshing=true
        getNewsList(Int.MAX_VALUE)
    }

    private fun getNewsList(endSid:Int){
        NewsApiRepository.getInstance()!!.getCnBetaNews(endSid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<CnBetaNewsItem>> {
                    override fun onSuccess(t: List<CnBetaNewsItem>) {
                        swipeLayout.isRefreshing=false
                        if (mPageIndex==1){
                            mAdapter!!.setNewData(t)
                        }else{
                            mAdapter!!.addData(t)
                            mAdapter!!.loadMoreComplete()
                        }
                        if (t.size==0){
                            mAdapter!!.loadMoreEnd()
                        }
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                        swipeLayout.isRefreshing=false
                        mAdapter!!.loadMoreComplete()
                    }

                })
    }



}

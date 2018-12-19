package com.orange.module_news.ui.jiandan


import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.orange.module_base.base.BaseFragment
import com.orange.module_base.base.BaseLazyFragment
import com.orange.module_news.R
import com.orange.module_news.R.id.recyclerview
import com.orange.module_news.model.NewsListItemBean
import com.orange.module_news.network.NewsApiRepository
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.module_news_fragment_news_jian_dan.*

/**
 * @author OrangeHao
 * @date 2018/12/17
 * @Github https://github.com/OrangeHao
 * @describe
 */
class NewsJianDanFragment : BaseLazyFragment() {
    override fun getLayoutId(): Int =R.layout.module_news_fragment_news_jian_dan


    var mAdapter: NewsJianDanListAdapter? = null
    val mDataList= ArrayList<NewsListItemBean.PostsBean>()

    var mPageIndex=1

    companion object {
        @JvmStatic
        fun newInstance(): NewsJianDanFragment {
            val args = Bundle()
            val fragment = NewsJianDanFragment()
            fragment.setArguments(args)
            return fragment
        }

    }


    override fun initView() {
        super.initView()

        mAdapter= NewsJianDanListAdapter(mDataList)
        recyclerview.addItemDecoration(DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerview.layoutManager= LinearLayoutManager(context)
        recyclerview.adapter=mAdapter

        mAdapter!!.setOnLoadMoreListener(object :BaseQuickAdapter.RequestLoadMoreListener{
            override fun onLoadMoreRequested() {
                mPageIndex++
                getNewsList()
            }
        },recyclerview)

        mAdapter!!.setOnItemClickListener { adapter, view, position ->
            JianDanNewsDetailActivity.start(context!!,mAdapter!!.data.get(position).id)
        }

        swipeLayout.setColorSchemeResources(R.color.colorPrimary)
        swipeLayout.setOnRefreshListener {
            mPageIndex=1
            getNewsList()
        }
    }

    override fun fetchData() {
        swipeLayout.isRefreshing=true
        getNewsList()
    }

    private fun getNewsList(){
        NewsApiRepository.getInstance()!!.getNews(mPageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :SingleObserver<List<NewsListItemBean.PostsBean>>{
                    override fun onSuccess(t: List<NewsListItemBean.PostsBean>) {
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

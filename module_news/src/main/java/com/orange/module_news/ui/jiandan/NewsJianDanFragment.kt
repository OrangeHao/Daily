package com.orange.module_news.ui.jiandan


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.orange.module_base.base.BaseFragment
import com.orange.module_news.R
import com.orange.module_news.model.NewsListItemBean
import kotlinx.android.synthetic.main.module_news_fragment_news_jian_dan.*

/**
 * @author OrangeHao
 * @date 2018/12/17
 * @Github https://github.com/OrangeHao
 * @describe
 */
class NewsJianDanFragment : BaseFragment() {
    override fun getLayoutId(): Int =R.layout.module_news_fragment_news_jian_dan


    var mAdapter: NewsJianDanListAdapter? = null
    val mDataList= ArrayList<NewsListItemBean.PostsBean>()

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
        recyclerview.layoutManager= LinearLayoutManager(context)
        recyclerview.adapter=mAdapter
    }





}

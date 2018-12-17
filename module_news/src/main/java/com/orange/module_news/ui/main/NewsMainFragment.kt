package com.orange.module_news.ui.main



import com.alibaba.android.arouter.facade.annotation.Route
import com.orange.module_base.base.BaseFragment
import com.orange.module_base.constants.ARouterPaths

import com.orange.module_news.R
import com.orange.module_news.ui.main.adapter.NewsHomePagerAdapter
import kotlinx.android.synthetic.main.module_news_fragment_news_main.*


/**
 * @author OrangeHao
 * @date 2018/12/14
 * @Github https://github.com/OrangeHao
 * @describe
 */
@Route(path = ARouterPaths.FRAGMENT_NewsMainFragment)
class NewsMainFragment : BaseFragment() {
    override fun getLayoutId(): Int =R.layout.module_news_fragment_news_main



    override fun initView() {
        super.initView()
        var mAdapter = NewsHomePagerAdapter(childFragmentManager,activity)
        view_pager.setOffscreenPageLimit(5)
        view_pager.setAdapter(mAdapter)
        sliding_tabs.setViewPager(view_pager)
        view_pager.setCurrentItem(0)
    }
}

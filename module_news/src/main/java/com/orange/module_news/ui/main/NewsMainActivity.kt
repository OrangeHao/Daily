package com.orange.module_news.ui.main

import com.alibaba.android.arouter.facade.annotation.Route
import com.orange.module_base.base.BaseActivity
import com.orange.module_base.constants.ARouterPaths
import com.orange.module_news.R

@Route(path = ARouterPaths.ACTIVITY_NEWS)
class NewsMainActivity : BaseActivity() {
    override fun getContentLayoutId(): Int =R.layout.module_news_activity_news

    override fun initView() {
        super.initView()
        val transition=supportFragmentManager.beginTransaction()
        transition.add(R.id.root_layout,NewsMainFragment())
        transition.commit()
    }
}

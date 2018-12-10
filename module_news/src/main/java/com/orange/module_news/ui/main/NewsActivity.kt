package com.orange.module_news.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.orange.module_base.constants.ARouterPaths
import com.orange.module_news.R

@Route(path = ARouterPaths.ACTIVITY_NEWS)
class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
    }
}

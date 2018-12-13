package com.orange.module_main.ui.main

import com.orange.module_base.base.BaseActivity
import com.orange.module_base.constants.ARouterPaths
import com.orange.module_base.utils.ActivityJumper
import com.orange.module_main.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(){

    override fun getContentLayoutId(): Int =R.layout.activity_main


    override fun initView() {
        news_btn.setOnClickListener {
            ActivityJumper.jumpActivity(ARouterPaths.ACTIVITY_NEWS)
        }
    }
}

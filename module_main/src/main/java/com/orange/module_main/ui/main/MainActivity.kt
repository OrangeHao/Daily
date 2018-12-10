package com.orange.module_main.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.orange.module_base.constants.ARouterPaths
import com.orange.module_base.utils.ActivityJumper
import com.orange.module_main.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }


    private fun init(){
        news_btn.setOnClickListener {
            ActivityJumper.jumpActivity(ARouterPaths.ACTIVITY_NEWS)
        }
    }
}

package com.orange.module_collector.ui.picture

import android.content.Context
import android.content.Intent
import com.orange.module_base.base.BaseActivity
import com.orange.module_collector.R
import com.orange.module_collector.beans.MediaBean


class VideoPlayActivity : BaseActivity(){
    override fun getContentLayoutId(): Int = R.layout.module_collector_activity_video_play

    companion object {

        @JvmStatic
        fun start(context: Context,bean: MediaBean) {
            val starter = Intent(context, VideoPlayActivity::class.java)
            starter.putExtra("data", bean)
            context.startActivity(starter)
        }
    }

    override fun initView() {
        super.initView()



    }

}
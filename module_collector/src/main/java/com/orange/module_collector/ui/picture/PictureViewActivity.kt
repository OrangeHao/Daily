package com.orange.module_collector.ui.picture

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.orange.module_base.base.BaseActivity
import com.orange.module_base.utils.statusbar.setStatusBarDarkMode
import com.orange.module_base.utils.statusbar.setTransParentStatusBar
import com.orange.module_collector.R
import com.orange.module_collector.beans.MediaBean
import com.orange.module_collector.ui.picture.adapter.PictureViewAdapter
import kotlinx.android.synthetic.main.module_collector_activity_photo_view.*
import java.io.Serializable
import java.util.ArrayList

class PictureViewActivity : BaseActivity() {
    override fun getContentLayoutId(): Int = R.layout.module_collector_activity_photo_view

    private lateinit var mAdapter: PictureViewAdapter
    private var mDataList: List<MediaBean> = ArrayList()

    companion object {

        @JvmStatic
        fun start(context: Context, datas: List<MediaBean>, position: Int) {
            val starter = Intent(context, PictureViewActivity::class.java)
            starter.putExtra("data", datas as Serializable)
            starter.putExtra("position", position)
            context.startActivity(starter)
        }
    }

    override fun initView() {
        super.initView()
        setTransParentStatusBar()
        setStatusBarDarkMode()

        mDataList= intent.getSerializableExtra("data") as List<MediaBean>
        mAdapter= PictureViewAdapter(mDataList)
        viewPager.adapter=mAdapter
        viewPager.setCurrentItem(intent.getIntExtra("position", 0))
    }

}
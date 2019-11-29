package com.orange.module_pictures.ui.jiandan


import android.content.Context
import android.content.Intent
import com.orange.module_base.base.BaseActivity
import com.orange.module_base.utils.statusbar.setStatusBarDarkMode
import com.orange.module_base.utils.statusbar.setTransParentStatusBar
import com.orange.module_pictures.R
import com.orange.module_pictures.ui.jiandan.adapter.PictureViewAdapter
import kotlinx.android.synthetic.main.module_pictures_activity_photo_view.*
import java.io.Serializable
import java.util.ArrayList

class PhotoViewActivity : BaseActivity() {
    override fun getContentLayoutId(): Int =R.layout.module_pictures_activity_photo_view

    private lateinit var mAdapter:PictureViewAdapter
    private var mDataList: List<String> = ArrayList()

    companion object {

        @JvmStatic
        fun start(context: Context, datas: List<String>, position: Int) {
            val starter = Intent(context, PhotoViewActivity::class.java)
            starter.putExtra("data", datas as Serializable)
            starter.putExtra("position", position)
            context.startActivity(starter)
        }
    }

    override fun initView() {
        super.initView()
        setTransParentStatusBar()
        setStatusBarDarkMode()

        mDataList= intent.getSerializableExtra("data") as List<String>
        mAdapter= PictureViewAdapter(mDataList)
        viewPager.adapter=mAdapter
        viewPager.setCurrentItem(intent.getIntExtra("position", 0))
    }

}

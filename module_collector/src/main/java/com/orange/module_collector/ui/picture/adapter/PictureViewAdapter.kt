package com.orange.module_collector.ui.picture.adapter

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.chrisbanes.photoview.PhotoView
import com.orange.module_base.utils.glide.ImageLoaderWrapper
import com.orange.module_collector.R
import com.orange.module_collector.beans.MediaBean
import com.orhanobut.logger.Logger

/**
 * created by czh on 2018/5/20
 */
class PictureViewAdapter(private val list: List<MediaBean>) : PagerAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, ob: Any): Boolean {
        return view === ob
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.module_collector_item_picture_view, null)
        val imgView=view.findViewById<PhotoView>(R.id.img)
        imgView.minimumScale=0.5f
        Logger.t("czh").d("displayer:"+list.get(position))
        ImageLoaderWrapper.loadImgDefault(container.context,list.get(position).path,imgView)
        container.addView(view)
        view.requestLayout()

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}

package com.orange.module_pictures.ui.jiandan.adapter

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.github.chrisbanes.photoview.PhotoView
import com.orange.module_base.utils.glide.ImageLoaderWrapper

import com.orange.module_pictures.R

/**
 * created by czh on 2018/5/20
 */
class PictureViewAdapter(private val list: List<String>) : PagerAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.module_pictures_item_picture_view, null)
        val imgView=view.findViewById<PhotoView>(R.id.img)
        imgView.minimumScale=0.5f
        val imgGif=view.findViewById<ImageView>(R.id.gif_mark)

        if (list.get(position).contains(".gif")){
            imgGif.visibility=View.VISIBLE
            ImageLoaderWrapper.loadImgAsGif(container.context,list.get(position),imgView)
        }else{
            imgGif.visibility=View.GONE
            ImageLoaderWrapper.loadImgDefault(container.context,list.get(position),imgView)
        }
        container.addView(view)
        view.requestLayout()

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}

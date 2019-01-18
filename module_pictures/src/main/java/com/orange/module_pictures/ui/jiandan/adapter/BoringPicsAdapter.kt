package com.orange.module_pictures.ui.jiandan.adapter

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.orange.module_base.utils.glide.GlideApp
import com.orange.module_base.utils.glide.ImageLoaderWrapper
import com.orange.module_pictures.R
import com.orange.module_pictures.model.BoringPicsBean
import com.orange.module_pictures.model.JianDanPicturesBean
import com.orange.module_pictures.utils.gif.GifThumbnailManager
import com.orhanobut.logger.Logger

/**
 * created by czh on 2018/12/27
 */
class BoringPicsAdapter(data: ArrayList<BoringPicsBean>?) : BaseMultiItemQuickAdapter<BoringPicsBean, BaseViewHolder>(data) {



    companion object {
        val TYPE_PIC = 0           //
        val TYPE_GIF = 1           //
    }

    init {
        addItemType(TYPE_PIC,R.layout.module_pictures_item_pics)
        addItemType(TYPE_GIF,R.layout.module_pictures_item_gif)
    }


    override fun convert(helper: BaseViewHolder, item: BoringPicsBean) {
        when(item.itemType){
            TYPE_PIC->{
                ImageLoaderWrapper.loadImgDefault(helper.itemView.context,
                        item.pics.get(0), helper.getView(R.id.pic_img))
            }
            TYPE_GIF->{
                val img=helper.getView(R.id.pic_img) as ImageView
                img.setTag(item.pics.get(0))
                GifThumbnailManager.getInstance().loadGifCover(item.pics.get(0),img)
            }
        }
    }


    private fun loadImg(context: Context,url:String,imageView: ImageView){
        GlideApp
                .with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .thumbnail(GlideApp
                        .with(context)
                        .load(url)
                )
                .into(imageView)
    }


    private fun getPicUrls(data: List<BoringPicsBean>): List<String> {
        val temp = java.util.ArrayList<String>()
        for (bean in data) {
            if (bean.pics != null) {
                temp.addAll(bean.pics)
            }
        }
        return temp
    }
}



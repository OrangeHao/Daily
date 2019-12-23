package com.orange.module_collector.ui.picture.adapter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.orange.module_base.utils.glide.GlideApp
import com.orange.module_collector.R
import com.orange.module_collector.beans.MediaBean
import com.orange.module_collector.utils.CalendarUtils

/**
 * created by czh on 2018/12/27
 */
class MediaListsAdapter(data: ArrayList<MediaBean>?) : BaseMultiItemQuickAdapter<MediaBean, BaseViewHolder>(data) {



    companion object {
        val TYPE_PIC = 0           //
        val TYPE_VIDEO = 1           //
        val TYPE_DATE_TITLE = 2           //
    }

    init {
        addItemType(TYPE_PIC, R.layout.module_collector_item_picture)
        addItemType(TYPE_VIDEO,R.layout.module_collector_item_video)
        addItemType(TYPE_DATE_TITLE,R.layout.module_collector_item_date_title)
    }


    override fun convert(helper: BaseViewHolder, item: MediaBean) {
        when(item.itemType){
            TYPE_DATE_TITLE->{
                helper.setText(R.id.dateText,CalendarUtils.getDateAndDayOfWeek(item.createTime))
            }
            TYPE_PIC->{
                loadImg(helper.itemView.context,item.path,helper.getView(R.id.pic_img))
            }
            TYPE_VIDEO->{

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

}



package com.orange.module_collector.ui.picture.adapter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseSectionMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.orange.module_base.utils.glide.GlideApp
import com.orange.module_collector.R
import com.orange.module_collector.beans.MediaBean
import com.orange.module_collector.beans.MediaSection
import com.orange.module_collector.utils.CalendarUtils

/**
 * created by czh on 2018/12/27
 */
class MediaListsAdapter(data: ArrayList<MediaSection>?) : BaseSectionMultiItemQuickAdapter<MediaSection, BaseViewHolder>(R.layout.module_collector_item_date_title,data) {


    companion object {
        val TYPE_PIC = 0           //
        val TYPE_VIDEO = 1           //
        val TYPE_DATE_TITLE = 2           //
    }

    init {
        addItemType(TYPE_PIC, R.layout.module_collector_item_picture)
        addItemType(TYPE_VIDEO,R.layout.module_collector_item_video)
    }

    override fun convert(helper: BaseViewHolder, item: MediaSection?) {
        if (item==null){
            return
        }
        when(item.itemType){
            TYPE_PIC->{
                loadImg(helper.itemView.context,item.t.path,helper.getView(R.id.pic_img))
            }
            TYPE_VIDEO->{

            }
        }
    }

    override fun convertHead(helper: BaseViewHolder?, item: MediaSection?) {
        if (item==null){
            return
        }
        helper?.setText(R.id.dateText,item.header)
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



package com.orange.module_collector.ui.picture.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseSectionMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.orange.module_base.utils.glide.GlideApp
import com.orange.module_collector.R
import com.orange.module_collector.beans.MediaSection
import com.orange.module_collector.widget.CheckView

/**
 * created by czh on 2018/12/27
 */
class MediaListsAdapter(data: ArrayList<MediaSection>?) : BaseSectionMultiItemQuickAdapter<MediaSection, BaseViewHolder>(R.layout.module_collector_item_date_title, data) {


    var mMarkMode = false
//    var mMarkCollection= hashMapOf<Int,Int>()
    var mMarkCollection= mutableListOf<MediaSection>()

    companion object {
        val TYPE_PIC = 0           //
        val TYPE_VIDEO = 1           //
        val TYPE_DATE_TITLE = 2           //
    }

    init {
        addItemType(TYPE_PIC, R.layout.module_collector_item_picture)
        addItemType(TYPE_VIDEO, R.layout.module_collector_item_video)
    }

    override fun convert(helper: BaseViewHolder, item: MediaSection?) {
        if (item == null) {
            return
        }
        when (item.itemType) {
            TYPE_PIC -> {
                loadImg(helper.itemView.context, item.t.path, helper.getView(R.id.pic_img))
                helper.setGone(R.id.check_view, mMarkMode)
                setItemMarkStatus(item,helper.getView(R.id.check_view))
            }
            TYPE_VIDEO -> {
                loadCover(helper.itemView.context, item.t.path, helper.getView(R.id.cover_img))
                helper.setGone(R.id.check_view, mMarkMode)
                setItemMarkStatus(item,helper.getView(R.id.check_view))
            }
        }
    }

    override fun convertHead(helper: BaseViewHolder?, item: MediaSection?) {
        if (item == null) {
            return
        }
        helper?.setText(R.id.dateText, item.header)
    }


    private fun loadImg(context: Context, url: String, imageView: ImageView) {
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


    private fun setItemMarkStatus(item: MediaSection,view: CheckView){
        if (!mMarkMode){
            return
        }
        if (mMarkCollection.contains(item)){
            view.isEnabled=true
            view.setCountable(true)
            view.setCheckedNum(mMarkCollection.indexOf(item)+1)
        }else{
            view.isEnabled=false
            view.setCountable(true)
            view.setCheckedNum(CheckView.UNCHECKED)
        }
    }


    fun changeViewMode() {
        mMarkMode = !mMarkMode
        mMarkCollection.clear()
        notifyDataSetChanged()
    }


    fun markItem(view: View, position: Int) {
        val item =data[position]
        if (mMarkCollection.contains(item)){
            mMarkCollection.remove(item)
            notifyDataSetChanged()
        }else{
            mMarkCollection.add(item)
            val checkView=view.findViewById<CheckView>(R.id.check_view)
            checkView?.setCountable(true)
            checkView?.isEnabled=true
            checkView?.setCheckedNum(mMarkCollection.size)
        }
    }


    private fun loadCover(context: Context,url: String,imageView: ImageView) {
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        Glide.with(context)
            .setDefaultRequestOptions(
                RequestOptions()
                    .frame(3000000)
                    .centerCrop()
            )
            .load(url)
            .into(imageView)
    }

}



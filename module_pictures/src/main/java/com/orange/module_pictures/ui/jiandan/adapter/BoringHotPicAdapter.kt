package com.orange.module_pictures.ui.jiandan.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.orange.module_base.utils.glide.ImageLoaderWrapper
import com.orange.module_pictures.R
import com.orange.module_pictures.model.BoringHotPicsBean
import com.orange.module_pictures.utils.gif.GifThumbnailManager

/**
 * created by czh on 2018/12/27
 */
class BoringHotPicAdapter(data: ArrayList<BoringHotPicsBean>?) : BaseMultiItemQuickAdapter<BoringHotPicsBean, BaseViewHolder>(data) {

    companion object {
        val TYPE_PIC = 0           //
        val TYPE_GIF = 1           //
    }

    init {
        addItemType(TYPE_PIC,R.layout.module_pictures_item_pics)
        addItemType(TYPE_GIF,R.layout.module_pictures_item_gif)
    }

    override fun convert(helper: BaseViewHolder, item: BoringHotPicsBean) {

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
}

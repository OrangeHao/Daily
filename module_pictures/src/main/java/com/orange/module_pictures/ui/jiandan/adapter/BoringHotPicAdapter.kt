package com.orange.module_pictures.ui.jiandan.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.orange.module_base.utils.glide.ImageLoaderWrapper
import com.orange.module_pictures.R
import com.orange.module_pictures.model.BoringHotPicsBean

/**
 * created by czh on 2018/12/27
 */
class BoringHotPicAdapter(data: ArrayList<BoringHotPicsBean>?) : BaseQuickAdapter<BoringHotPicsBean, BaseViewHolder>(R.layout.module_pictures_item_pics, data) {


    override fun convert(helper: BaseViewHolder, item: BoringHotPicsBean) {
        if (item.pics != null) {
            ImageLoaderWrapper.loadImgDefault(helper.itemView.context,
                    item.pics.get(0), helper.getView(R.id.pic_img))
        }
    }
}

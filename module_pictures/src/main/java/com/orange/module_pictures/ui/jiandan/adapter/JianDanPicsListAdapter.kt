package com.orange.module_pictures.ui.jiandan.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.orange.module_base.utils.glide.ImageLoaderWrapper
import com.orange.module_base.utils.glide.ImageLoaderWrapper.Default_holder
import com.orange.module_pictures.R
import com.orange.module_pictures.utils.getSamllUrl

/**
 * @author OrangeHao
 * @date 2018/12/26
 * @Github https://github.com/OrangeHao
 * @describe
 */
class JianDanPicsListAdapter(data: ArrayList<String>?) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.module_pictures_item_pics, data) {


    override fun convert(helper: BaseViewHolder, item: String) {
        ImageLoaderWrapper.loadImgDefault(helper.itemView.context,
                item.getSamllUrl(), Default_holder,helper.getView(R.id.pic_img))
    }
}

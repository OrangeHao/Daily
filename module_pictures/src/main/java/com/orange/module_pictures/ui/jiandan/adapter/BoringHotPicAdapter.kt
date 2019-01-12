package com.orange.module_pictures.ui.jiandan.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.orange.module_base.utils.glide.ImageLoaderWrapper
import com.orange.module_pictures.R
import com.orange.module_pictures.model.BoringHotPicsBean
import com.orhanobut.logger.Logger
import com.silencedut.taskscheduler.TaskScheduler

/**
 * created by czh on 2018/12/27
 */
class BoringHotPicAdapter(data: ArrayList<BoringHotPicsBean>?) : BaseQuickAdapter<BoringHotPicsBean, BaseViewHolder>(R.layout.module_pictures_item_pics, data) {


    override fun convert(helper: BaseViewHolder, item: BoringHotPicsBean) {
        if (item.pics != null) {
            if (item.pics.get(0).contains(".gif")){
                Logger.t("czh").d("gif")
                TaskScheduler.execute(GifThumnailTask(item.pics.get(0),helper.getView(R.id.pic_img)))
                helper.setGone(R.id.gif_mark,true)
            }else{
                helper.setGone(R.id.gif_mark,false)
                ImageLoaderWrapper.loadImgDefault(helper.itemView.context,
                        item.pics.get(0), helper.getView(R.id.pic_img))
            }
        }
    }
}

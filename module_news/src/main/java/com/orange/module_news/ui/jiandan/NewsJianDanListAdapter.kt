package com.orange.module_news.ui.jiandan

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.orange.module_base.utils.glide.ImageLoaderWrapper
import com.orange.module_news.R
import com.orange.module_news.model.Post

/**
 * @author OrangeHao
 * @date 2018/12/17
 * @Github https://github.com/OrangeHao
 * @describe
 */
class NewsJianDanListAdapter(data: ArrayList<Post>?) : BaseQuickAdapter<Post, BaseViewHolder>(R.layout.module_news_item_jiandan_news_list, data) {


    override fun convert(helper: BaseViewHolder, item: Post) {

        ImageLoaderWrapper.loadImgDefault(helper.itemView.context, item.custom_fields!!.thumb_c?.get(0), helper.getView(R.id.news_img))

        helper.setText(R.id.news_title, item.title)
        helper.setText(R.id.news_content, item.excerpt)
        helper.setText(R.id.news_time, item.date)
        helper.setText(R.id.news_comment_count, item.comment_count.toString() + "")
    }
}

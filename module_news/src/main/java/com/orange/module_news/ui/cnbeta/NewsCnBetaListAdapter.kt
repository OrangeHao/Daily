package com.orange.module_news.ui.cnbeta

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.orange.module_base.utils.glide.ImageLoaderWrapper
import com.orange.module_news.R
import com.orange.module_news.model.CnBetaNewsItem
import com.orange.module_news.model.Post

/**
 * @author OrangeHao
 * @date 2018/12/17
 * @Github https://github.com/OrangeHao
 * @describe
 */
class NewsCnBetaListAdapter(data: ArrayList<CnBetaNewsItem>?) : BaseQuickAdapter<CnBetaNewsItem, BaseViewHolder>(R.layout.module_news_cnbeta_news_item, data) {


    override fun convert(helper: BaseViewHolder, item: CnBetaNewsItem) {

        ImageLoaderWrapper.loadImgDefault(helper.itemView.context, item.thumb, helper.getView(R.id.news_img))

        helper.setText(R.id.news_title, item.title)
        helper.setText(R.id.news_time, item.pubtime)
        helper.setText(R.id.news_comment_count, item.comments+"/"+item.counter)
    }
}

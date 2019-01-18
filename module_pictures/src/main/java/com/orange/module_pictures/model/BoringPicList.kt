package com.orange.module_pictures.model

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.orange.module_pictures.ui.jiandan.adapter.BoringHotPicAdapter
import org.w3c.dom.Comment

/**
 * created by czh on 2018/12/27
 */
data class BoringPicList(
        val comments: List<BoringPicsBean>,
        val count: Int,
        val current_page: Int,
        val page_count: Int,
        val status: String,
        val total_comments: Int
)

data class BoringPicsBean(
    val comment_ID: String,
    val comment_author: String,
    val comment_content: String,
    val comment_date: String,
    val comment_date_gmt: String,
    val comment_post_ID: String,
    val pics: List<String>,
    val sub_comment_count: String,
    val text_content: String,
    val user_id: String,
    val vote_negative: String,
    val vote_positive: String
):MultiItemEntity{

    override fun getItemType(): Int {
        if (pics.get(0).contains(".gif")){
            return BoringHotPicAdapter.TYPE_GIF
        }
        return return BoringHotPicAdapter.TYPE_PIC
    }

}


data class BoringHotPicList(
    val comments: List<BoringHotPicsBean>,
    val status: String
)

data class BoringHotPicsBean(
    val _extra: BoringExtra,
    val comment_ID: String,
    val comment_author: String,
    val comment_date: String,
    val parent_link: String,
    val parent_title: String,
    val pics: List<String>,
    val sub_comment_count: String,
    val text_content: String,
    val user_id: Int,
    val vote_negative: String,
    val vote_positive: String
):MultiItemEntity{

    override fun getItemType(): Int {
        if (pics.get(0).contains(".gif")){
            return BoringHotPicAdapter.TYPE_GIF
        }
        return return BoringHotPicAdapter.TYPE_PIC
    }

}

data class BoringExtra(
    val category_type: String,
    val page_num: String
)
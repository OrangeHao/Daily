package com.orange.module_pictures.model


/**
 * created by czh on 2018/5/11
 */
data class PicsBean(
        val comments: List<JianDanPicturesBean>,
        val status: String
)

data class JianDanPicturesBean(
    val _extra: Extra,
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
)

data class Extra(
    val category_type: String,
    val page_num: String
)
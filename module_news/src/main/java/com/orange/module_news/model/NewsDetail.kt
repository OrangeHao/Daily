package com.orange.module_news.model

/**
 * created by czh on 2018/7/28
 */
data class NewsDetail(
    val post: NewsDetailPost,
    val previous_url: String,
    val status: String
)

data class NewsDetailPost(
    val content: String,
    val date: String,
    val id: Int,
    val modified: String
)
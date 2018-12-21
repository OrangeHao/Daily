package com.orange.module_news.model

/**
 * created by czh on 2018/12/21
 */
data class CnBetaNewsListBean(
    val code: Int,
    val msg: String,
    val result: List<CnBetaNewsItem>,
    val status: String
)

data class CnBetaNewsItem(
    val comments: String,
    val counter: String,
    val pubtime: String,
    val ratings: String,
    val ratings_story: String,
    val score: String,
    val score_story: String,
    val sid: String,
    val summary: String,
    val thumb: String,
    val title: String,
    val topic: String,
    val topic_logo: String
)
package com.orange.module_news.model

/**
 * created by czh on 2018/12/25
 */
data class CnbetaNewsDetail(
    val code: Int,
    val msg: String,
    val result: Result,
    val status: String
)

data class Result(
    val aid: String,
    val bad: String,
    val bodytext: String,
    val brief: String,
    val catid: String,
    val collectnum: String,
    val comments: String,
    val counter: String,
    val data_id: String,
    val elite: String,
    val good: String,
    val hometext: String,
    val ifcom: String,
    val inputtime: String,
    val ishome: String,
    val keywords: String,
    val listorder: String,
    val mview: String,
    val pollid: String,
    val premium: String,
    val queueid: String,
    val ratings: String,
    val ratings_story: String,
    val relation: String,
    val relation_show: String,
    val score: String,
    val score_story: String,
    val shorttitle: String,
    val sid: String,
    val source: String,
    val sourceid: String,
    val status: String,
    val style: String,
    val thumb: String,
    val time: String,
    val title: String,
    val topic: String,
    val updatetime: String,
    val user_id: String
)
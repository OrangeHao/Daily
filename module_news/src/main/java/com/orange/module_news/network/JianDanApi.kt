package com.orange.module_news.network

import com.orange.module_news.model.NewsListItemBean
import com.orange.module_news.model.NewsDetail

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * created by czh on 2018/5/9
 */
interface JianDanApi {


    //新闻列表
    //@GET("http://i.jandan.net/?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,excerpt,comment_count,comment_status,custom_fields&page={pageIndex}&custom_fields=thumb_c,views&dev=1")
    @GET("http://i.jandan.net/")
    fun getNews(
            @Query("oxwlxojflwblxbsapi") way: String,
            @Query("include") include: String,
            @Query("page") page: String,
            @Query("custom_fields") custom_fields: String,
            @Query("dev") dev: String): Single<NewsListItemBean>


    //新闻详情
    //http://i.jandan.net/?oxwlxojflwblxbsapi=get_post&id=97943&include=content,date,modified
    @GET("http://i.jandan.net/")
    fun getNewsDetail(
            @Query("oxwlxojflwblxbsapi") way: String,
            @Query("id") id: String,
            @Query("include") include: String): Single<NewsDetail>


}

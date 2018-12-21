package com.orange.module_news.network

import com.orange.module_news.model.CnBetaNewsListBean
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * created by czh on 2018/12/21
 */
interface CnBetaApi {


    //http://api.cnbeta.com/capi?app_key=10000&end_sid=2147483647&format=json&method=Article.Lists&timestamp=1545375078371&v=2.8.5&sign=cc47170f84c100d554dd3e5e15877d79
    @GET("http://api.cnbeta.com/capi")
    abstract fun getNewsByEndId(
            @Query("app_key") app_key:String,
            @Query("end_sid") end_sid:String,
            @Query("format") format:String,
            @Query("method") method:String,
            @Query("timestamp") timestamp:String,
            @Query("v") v:String,
            @Query("sign") sign:String
    ): Single<CnBetaNewsListBean>

}
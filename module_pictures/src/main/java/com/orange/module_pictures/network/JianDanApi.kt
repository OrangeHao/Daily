package com.orange.module_pictures.network


import com.orange.module_pictures.model.PicsBean

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * created by czh on 2018/5/9
 */
interface JianDanApi {


    //图片列表
    //http://i.jandan.net/?oxwlxojflwblxbsapi=jandan.get_ooxx_comments&page=1
    @GET("http://i.jandan.net/")
    fun getHandyPics(
            @Query("oxwlxojflwblxbsapi") way: String,
            @Query("page") page: String): Single<PicsBean>

    //http://api.moyu.today/jandan/hot?category=ooxx
    @GET("http://api.moyu.today/jandan/hot")
    fun getHandyHotPics(
            @Query("category") way: String): Single<PicsBean>

}

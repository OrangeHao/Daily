package com.orange.module_news.network


import com.orange.module_news.model.NewsDetail
import com.orange.module_news.model.NewsDetailPost
import com.orange.module_news.model.NewsListItemBean
import com.orange.module_news.model.Post

import io.reactivex.Single
import retrofit2.Retrofit

/**
 * created by czh on 2018/5/9
 */
class NewsApiRepository private constructor() {
    private val mRetrofit: Retrofit
    private val mJianDanApi: JianDanApi

    init {
        mRetrofit = NewsRefrofitHelper().retrofit
        mJianDanApi = mRetrofit.create(JianDanApi::class.java)
    }

    companion object {

        private var sInstance: NewsApiRepository? = null

        @JvmStatic
        fun getInstance(): NewsApiRepository?{
            if (sInstance == null) {
                synchronized(NewsApiRepository::class.java) {
                    if (sInstance == null) {
                        sInstance = NewsApiRepository()
                    }
                }
            }
            return sInstance
        }
    }


    fun getNews(pageIndex: Int): Single<List<Post>> {
        return mJianDanApi.getNews(
                "get_recent_posts",
                "url,date,tags,author,title,excerpt,comment_count,comment_status,custom_fields",
                "" + pageIndex,
                "thumb_c,views",
                "1")
                .map { bean -> bean.posts }
    }

    fun getNewsDetail(id: Int): Single<NewsDetailPost> {
        return mJianDanApi.getNewsDetail(
                "get_post",
                "" + id,
                "content,date,modified")
                .map { newsBean -> newsBean.post }
    }

}

package com.orange.module_news.network


import com.orange.module_news.model.CnBetaNewsItem
import com.orange.module_news.model.CnbetaNewsDetail
import com.orange.module_news.model.NewsDetailPost
import com.orange.module_news.model.Post
import com.orange.module_news.utils.MD5

import io.reactivex.Single
import retrofit2.Retrofit

/**
 * created by czh on 2018/5/9
 */
class NewsApiRepository private constructor() {
    private val mRetrofit: Retrofit
    private val mJianDanApi: JianDanApi

    private val mCnBetaApi:CnBetaApi

    init {
        mRetrofit = NewsRefrofitHelper().retrofit
        mJianDanApi = mRetrofit.create(JianDanApi::class.java)
        mCnBetaApi = mRetrofit.create(CnBetaApi::class.java)
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

    fun getCnBetaNews(endSid:Int):Single<List<CnBetaNewsItem>>{
        val sb = StringBuilder()
        sb.append("app_key=10000")
        sb.append("&end_sid=").append(endSid)
        sb.append("&format=json")
        sb.append("&method=Article.Lists")
        val time=System.currentTimeMillis()
        sb.append("&timestamp=").append(time)
        sb.append("&v=2.8.5")
        val signed = MD5.md5(sb.toString() + "&mpuffgvbvbttn3Rc")
        return mCnBetaApi.getNewsByEndId(
                "10000",
                 endSid.toString(),
                "json",
                "Article.Lists",
                time.toString(),
                "2.8.5",
                signed
        ).map { bean->bean.result}
    }

    fun getCnBetaNewsContent(sid:String):Single<CnbetaNewsDetail>{
        val sb = StringBuilder()
        sb.append("app_key=10000")
        sb.append("&format=json")
        sb.append("&method=Article.NewsContent")
        sb.append("&sid=").append(sid)
        val time=System.currentTimeMillis()
        sb.append("&timestamp=").append(time)
        sb.append("&v=2.8.5")
        val signed = MD5.md5(sb.toString() + "&mpuffgvbvbttn3Rc")
        return mCnBetaApi.getNewsContentBySid(
                "10000",
                "json",
                "Article.NewsContent",
                sid.toString(),
                time.toString(),
                "2.8.5",
                signed
        )
    }

}

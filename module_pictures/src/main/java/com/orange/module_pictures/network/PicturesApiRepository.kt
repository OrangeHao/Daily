package com.orange.module_pictures.network


import com.orange.module_pictures.model.JianDanPicturesBean
import com.orange.module_pictures.model.PicsBean
import io.reactivex.Single
import retrofit2.Retrofit

/**
 * created by czh on 2018/5/9
 */
class PicturesApiRepository private constructor() {
    private val mRetrofit: Retrofit
    private val mJianDanApi: JianDanApi


    init {
        mRetrofit = PicturesRefrofitHelper().retrofit
        mJianDanApi = mRetrofit.create(JianDanApi::class.java)
    }

    companion object {

        private var sInstance: PicturesApiRepository? = null

        @JvmStatic
        fun getInstance(): PicturesApiRepository?{
            if (sInstance == null) {
                synchronized(PicturesApiRepository::class.java) {
                    if (sInstance == null) {
                        sInstance = PicturesApiRepository()
                    }
                }
            }
            return sInstance
        }
    }


    fun getHandyPics(pageIndex: Int): Single<List<JianDanPicturesBean>> {
        return mJianDanApi.getHandyPics(
                "jandan.get_ooxx_comments",
                "" + pageIndex)
                .map { bean -> bean.comments }
    }

    fun getHandyHotPics(): Single<List<JianDanPicturesBean>> {
        return mJianDanApi.getHandyHotPics(
                "ooxx").map { bean -> bean.comments }
    }


}

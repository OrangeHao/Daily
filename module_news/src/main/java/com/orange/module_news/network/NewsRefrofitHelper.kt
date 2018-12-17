package com.orange.module_news.network

import com.orange.module_base.network.BaseRetrofitHelper

/**
 * created by czh on 2018/12/17
 */
class NewsRefrofitHelper: BaseRetrofitHelper() {

    companion object {
        private var sInstance: NewsRefrofitHelper? = null

        fun getInstance(): BaseRetrofitHelper?{
            if (sInstance == null) {
                synchronized(BaseRetrofitHelper::class.java) {
                    if (sInstance == null) {
                        sInstance = NewsRefrofitHelper()
                    }
                }
            }
            return sInstance
        }
    }

    override fun getBaseUrl(): String {
        return ""
    }

}

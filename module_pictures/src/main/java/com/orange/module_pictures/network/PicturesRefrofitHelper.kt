package com.orange.module_pictures.network

import com.orange.module_base.network.BaseRetrofitHelper

/**
 * created by czh on 2018/12/17
 */
class PicturesRefrofitHelper: BaseRetrofitHelper() {

    override fun getBaseUrl(): String {
        return "http://i.jandan.net"
    }

}

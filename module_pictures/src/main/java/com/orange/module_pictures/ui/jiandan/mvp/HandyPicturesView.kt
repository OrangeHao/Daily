package com.orange.module_pictures.ui.jiandan.mvp


import com.orange.module_base.base.BaseView

/**
 * created by czh on 2018/5/11
 */
interface HandyPicturesView : BaseView {

    fun getRefreshDatas(data: List<String>)
    fun getLoadMoreDatas(data: List<String>)
}

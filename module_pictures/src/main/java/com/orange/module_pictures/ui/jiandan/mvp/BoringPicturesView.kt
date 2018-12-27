package com.orange.module_pictures.ui.jiandan.mvp

import com.orange.module_base.base.BaseView
import com.orange.module_pictures.model.BoringHotPicsBean
import com.orange.module_pictures.model.BoringPicsBean

/**
 * created by czh on 2018/12/27
 */
interface BoringPicturesView : BaseView {

    fun getRefreshDatas(data: List<BoringPicsBean>)
    fun getLoadMoreDatas(data: List<BoringPicsBean>)

    fun getHotDatas(data: List<BoringHotPicsBean>)
}

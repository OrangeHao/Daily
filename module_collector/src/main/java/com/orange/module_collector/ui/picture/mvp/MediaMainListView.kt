package com.orange.module_pictures.ui.jiandan.mvp

import com.orange.module_base.base.BaseView
import com.orange.module_collector.beans.MediaBean

/**
 * created by czh on 2018/12/27
 */
interface MediaMainListView : BaseView {

    fun receiveData(data: List<MediaBean>)
}

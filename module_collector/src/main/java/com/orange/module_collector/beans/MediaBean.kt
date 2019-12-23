package com.orange.module_collector.beans

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.orange.module_collector.ui.picture.adapter.MediaListsAdapter
import java.io.Serializable

/**
 * created by czh on 2019/11/30
 */
data class MediaBean(var name: String="", var path: String="", var createTime: Long=0L) : MultiItemEntity,Serializable {


    private var mBeanType=MediaListsAdapter.TYPE_PIC

    override fun getItemType(): Int {
        return mBeanType
    }

    fun setBeanType(type:Int){
        mBeanType=type
    }

}
package com.orange.module_collector.beans

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.orange.module_collector.ui.picture.adapter.MediaListsAdapter
import java.io.Serializable

/**
 * created by czh on 2019/11/30
 */
data class MediaBean(var name: String, var path: String, var createTime: Long) : MultiItemEntity,Serializable {

    override fun getItemType(): Int {
        if (path.contains(".gif")){
            return MediaListsAdapter.TYPE_VIDEO
        }
        return return MediaListsAdapter.TYPE_PIC
    }

}
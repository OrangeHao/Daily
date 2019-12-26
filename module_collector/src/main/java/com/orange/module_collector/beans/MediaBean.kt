package com.orange.module_collector.beans

import android.os.Parcel
import android.os.Parcelable
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.chad.library.adapter.base.entity.SectionEntity
import com.orange.module_collector.ui.picture.adapter.MediaListsAdapter
import java.io.Serializable

/**
 * created by czh on 2019/11/30
 */
data class MediaBean(var name: String = "", var path: String = "", var createTime: Long = 0L) : MultiItemEntity, Parcelable {
    private var mBeanType = MediaListsAdapter.TYPE_PIC

    override fun getItemType(): Int {
        return mBeanType
    }

    fun setBeanType(type: Int) {
        mBeanType = type
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readLong()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(path)
        writeLong(createTime)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MediaBean> = object : Parcelable.Creator<MediaBean> {
            override fun createFromParcel(source: Parcel): MediaBean = MediaBean(source)
            override fun newArray(size: Int): Array<MediaBean?> = arrayOfNulls(size)
        }
    }
}
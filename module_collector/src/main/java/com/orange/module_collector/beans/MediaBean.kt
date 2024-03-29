package com.orange.module_collector.beans

import android.os.Parcel
import android.os.Parcelable
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.orange.module_collector.common.VIDEO_POSTFIX
import com.orange.module_collector.ui.picture.adapter.MediaListsAdapter

/**
 * created by czh on 2019/11/30
 */
data class MediaBean(var name: String = "", var path: String = "", var createTime: Long = 0L) : MultiItemEntity, Parcelable {
    var mBeanType = MediaListsAdapter.TYPE_PIC

    init{
        if (name.contains(VIDEO_POSTFIX)){
            mBeanType=MediaListsAdapter.TYPE_VIDEO
        }else{
            mBeanType=MediaListsAdapter.TYPE_PIC
        }
    }

    override fun getItemType(): Int {
        return mBeanType
    }

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong()
    ) {
        mBeanType = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(path)
        parcel.writeLong(createTime)
        parcel.writeInt(mBeanType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MediaBean> {
        override fun createFromParcel(parcel: Parcel): MediaBean {
            return MediaBean(parcel)
        }

        override fun newArray(size: Int): Array<MediaBean?> {
            return arrayOfNulls(size)
        }
    }


}
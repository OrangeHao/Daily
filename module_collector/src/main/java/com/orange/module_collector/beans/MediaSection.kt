package com.orange.module_collector.beans

import com.chad.library.adapter.base.entity.SectionMultiEntity

/**
 * created by czh on 2019/12/26
 */
class MediaSection : SectionMultiEntity<MediaBean> {

    constructor(ishead: Boolean, head: String) : super(ishead, head) {

    }

    constructor(bean: MediaBean) : super(bean) {

    }

    var isMarked = false

    override fun getItemType(): Int {
        if (t != null) {
            return t.itemType
        }
        return 1
    }


    override fun equals(other: Any?): Boolean {
        if (other == null
                || other !is MediaSection
                || (this.t != null && other.t == null)
                || (this.t == null && other.t != null)
                || (this.t != null && other.t != null && !this.t.path.equals(other.t.path))) {
            return false
        } else {
            return true
        }
    }
}
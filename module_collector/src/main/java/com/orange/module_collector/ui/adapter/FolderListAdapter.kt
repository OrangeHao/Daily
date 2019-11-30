package com.orange.module_collector.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.orange.module_collector.R
import com.orange.module_collector.beans.FolderBean

/**
 * created by czh on 2019/11/30
 */
class FolderListAdapter(data: ArrayList<FolderBean>?)
    : BaseQuickAdapter<FolderBean, BaseViewHolder>(R.layout.module_collector_folder_item, data) {


    override fun convert(helper: BaseViewHolder, item: FolderBean) {
        if (helper.layoutPosition==data.size-1){
            helper.setImageResource(R.id.img_folder,R.drawable.module_collector_ic_add_folder)
            helper.setGone(R.id.text_name,false)
        }else{
            helper.setImageResource(R.id.img_folder,R.drawable.module_collector_ic_folder)
            helper.setText(R.id.text_name,item.name)
            helper.setGone(R.id.text_name,true)
        }
    }
}

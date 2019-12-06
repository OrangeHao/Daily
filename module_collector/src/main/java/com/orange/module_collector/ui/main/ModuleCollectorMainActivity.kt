package com.orange.module_collector.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Environment
import com.orange.module_base.base.BaseActivity
import com.orange.module_base.utils.checkPermissionWriteExteralStorage
import com.orange.module_collector.R
import com.orange.module_collector.ui.picture.MediaMainListFragment
import kotlinx.android.synthetic.main.module_collector_activity_main.*
import java.io.File


class ModuleCollectorMainActivity : BaseActivity() {
    override fun getContentLayoutId(): Int = R.layout.module_collector_activity_main


    override fun initView() {

        checkPermissionWriteExteralStorage {
            if (!it){
                finish()
            }
        }


        val path1= Environment.getExternalStorageDirectory().toString()
        val path2= Environment.getExternalStoragePublicDirectory("aaa").toString()



        val intent = intent
        val action = intent.action
        val type = intent.type


        showList()

    }


    private fun showList(){
        val transition=supportFragmentManager.beginTransaction()
        transition.add(R.id.root_layout,MediaMainListFragment())
        transition.commit()
    }

}
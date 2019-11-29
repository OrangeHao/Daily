package com.orange.module_collector

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import com.orange.module_base.base.BaseActivity
import com.orange.module_base.utils.checkPermissionWriteExteralStorage
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

        if (Intent.ACTION_SEND == action && type != null) {
            if (type.startsWith("image/")) {
                handleSendImage(intent)
            }
        } else if (Intent.ACTION_SEND_MULTIPLE == action && type != null) {
            if (type.startsWith("image/")) {
                handleSendMultipleImages(intent)
            }
        }
    }

    fun handleSendImage(intent: Intent) {
        val imageUri = intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
        if (imageUri != null) {
            val file= File(imageUri.path)
            testTextView.text=imageUri.encodedPath+file.exists()

            testImage.setImageURI(imageUri)
        }
    }

    fun handleSendMultipleImages(intent: Intent) {
        val imageUris = intent.getParcelableArrayListExtra<Uri>(Intent.EXTRA_STREAM)
        val stringBuilder=StringBuilder()
        if (imageUris != null) {
            for (item in imageUris){
                stringBuilder.append(item.path).append("\n")
            }
        }
        testTextView.text=stringBuilder.toString()
    }

}
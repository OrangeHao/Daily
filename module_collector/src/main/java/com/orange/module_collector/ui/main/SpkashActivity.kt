package com.orange.module_collector.ui.main

import android.content.Intent
import android.os.Handler
import com.orange.module_base.base.BaseActivity
import com.orange.module_base.utils.checkPermissionWriteExteralStorage
import com.orange.module_base.utils.statusbar.setStatusBarDarkMode
import com.orange.module_base.utils.statusbar.setTransParentStatusBar
import com.orange.module_collector.R

class SpkashActivity : BaseActivity() {
    override fun getContentLayoutId(): Int = R.layout.module_collector_activity_spkash


    override fun initView() {
        setTransParentStatusBar()
        setStatusBarDarkMode()

        checkPermissionWriteExteralStorage {
            if (it){
                Handler().postDelayed({
                    startActivity(Intent(mContext, ModuleCollectorMainActivity::class.java))
                    finish()
                }, 1000 * 2)
            }
        }


    }


}

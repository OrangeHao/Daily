package com.orange.module_collector.ui.main

import android.os.Environment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.orange.module_base.base.BaseActivity
import com.orange.module_base.utils.checkPermissionWriteExteralStorage
import com.orange.module_collector.R
import com.orange.module_collector.ui.picture.MediaMainListFragment
import kotlinx.android.synthetic.main.module_collector_content_main.*


class ModuleCollectorMainActivity : BaseActivity() {
    override fun getContentLayoutId(): Int = R.layout.module_collector_activity_main


    private var mFragments = arrayListOf<Fragment>()


    override fun initView() {

        checkPermissionWriteExteralStorage {
            if (!it) {
                finish()
            }
        }

        initViewPager()
    }


    private fun initViewPager() {
        mFragments.add(MediaMainListFragment())
        viewPager.offscreenPageLimit = 3
        viewPager.adapter = object :
                FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int) = mFragments.get(position)

            override fun getCount() = mFragments.size
        }
    }


    private fun showList() {
        val transition = supportFragmentManager.beginTransaction()
        transition.add(R.id.container, MediaMainListFragment())
        transition.commit()
    }

}
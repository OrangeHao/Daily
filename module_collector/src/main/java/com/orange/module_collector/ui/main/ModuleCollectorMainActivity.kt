package com.orange.module_collector.ui.main

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.orange.module_base.base.BaseActivity
import com.orange.module_base.utils.checkPermissionWriteExteralStorage
import com.orange.module_collector.R
import com.orange.module_collector.ui.picture.MediaMainListFragment
import kotlinx.android.synthetic.main.module_collector_activity_main.*
import kotlinx.android.synthetic.main.module_collector_content_main.*


class ModuleCollectorMainActivity : BaseActivity() {
    override fun getContentLayoutId(): Int = R.layout.module_collector_activity_main


    private var mFragments = arrayListOf<Fragment>()
    private var mDrawerToggle:ActionBarDrawerToggle?=null

    override fun initView() {

        checkPermissionWriteExteralStorage {
            if (!it) {
                finish()
            }
        }
        setupActionBar()
        initDrawer()
        initViewPager()
    }


    //设置ToolBar
    private fun setupActionBar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    //初始化侧边栏
    private fun initDrawer() {
        mDrawerToggle = ActionBarDrawerToggle(this, drawer_layout, R.string.module_collector_navigation_drawer_open, R.string.module_collector_navigation_drawer_close)
        mDrawerToggle!!.syncState()
        drawer_layout.addDrawerListener(mDrawerToggle!!)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // 这个必须要，没有的话进去的默认是个箭头。。正常应该是三横杠的
        mDrawerToggle!!.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        mDrawerToggle!!.onConfigurationChanged(newConfig)
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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.module_collector_main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_share->{
                (mFragments.get(viewPager.currentItem) as MenuActionListener).actionById(R.id.action_share)
            }
            R.id.action_delete->{

            }
            android.R.id.home->{
                if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    drawer_layout.closeDrawers()
                } else {
                    drawer_layout.openDrawer(GravityCompat.START, true)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


    interface MenuActionListener{
        fun actionById(actionId:Int)
    }
}
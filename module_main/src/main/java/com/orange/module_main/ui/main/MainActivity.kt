package com.orange.module_main.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import com.orange.module_base.base.BaseActivity
import com.orange.module_base.base.BaseFragment
import com.orange.module_base.constants.ARouterPaths
import com.orange.module_base.utils.ARouterUtils
import com.orange.module_main.R
import kotlinx.android.synthetic.main.module_main_activity_main.*
import kotlinx.android.synthetic.main.module_main_content_main.*
import java.util.ArrayList

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener,BottomNavigationView.OnNavigationItemSelectedListener {
    override fun getContentLayoutId(): Int =R.layout.module_main_activity_main


    private val mFragmentList = ArrayList<Fragment>()

    private var mNewsFragment:BaseFragment?=null
    private var mPicturesFragment:BaseFragment?=null

    override fun initView() {
        super.initView()
        initNavigation()

        switchFragment(ARouterPaths.FRAGMENT_NewsMainFragment)
    }


    private fun initNavigation(){
        nav_view.setNavigationItemSelectedListener(this)
        navigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
            }
            R.id.nav_gallery -> {
            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
            R.id.navigation_news -> {
                switchFragment(ARouterPaths.FRAGMENT_NewsMainFragment)
            }
            R.id.navigation_pictures -> {
                switchFragment(ARouterPaths.FRAGMENT_PicturesMainFragment)
            }
            R.id.navigation_notifications -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    fun switchFragment(tag: String) {
        hideFragment()
        val transaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment != null) {
            transaction.show(fragment)
        } else {
            if (TextUtils.equals(tag, ARouterPaths.FRAGMENT_NewsMainFragment)) {
                if (mNewsFragment==null){
                    mNewsFragment=ARouterUtils.getFragment(ARouterPaths.FRAGMENT_NewsMainFragment)
                }
                fragment = mNewsFragment
            } else if (TextUtils.equals(tag, ARouterPaths.FRAGMENT_PicturesMainFragment)) {
                if (mPicturesFragment==null){
                    mPicturesFragment=ARouterUtils.getFragment(ARouterPaths.FRAGMENT_PicturesMainFragment)
                }
                fragment = mPicturesFragment
            } else {
                if (mNewsFragment==null){
                    mNewsFragment=ARouterUtils.getFragment(ARouterPaths.FRAGMENT_NewsMainFragment)
                }
                fragment = mNewsFragment
            }
            mFragmentList.add(fragment)
            transaction.add(R.id.container, fragment, tag)
        }
        transaction.commitAllowingStateLoss()
    }


    /**
     * 隐藏所有Fragment
     */
    private fun hideFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        for (f in mFragmentList) {
            transaction.hide(f)
        }
        transaction.commit()

    }
}

package com.orange.module_pictures.ui.main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.orange.module_pictures.R
import com.orange.module_pictures.ui.jiandan.BoringFragment
import com.orange.module_pictures.ui.jiandan.BoringHotFragment
import com.orange.module_pictures.ui.jiandan.HandyFragment
import com.orange.module_pictures.ui.jiandan.HandyHotFragment


/**
 * @author OrangeHao
 * @date 2018/12/17
 * @Github https://github.com/OrangeHao
 * @describe
 */
class PictureHomePagerAdapter(fm: FragmentManager, context: Context) : FragmentPagerAdapter(fm) {

    private val TITLES: Array<String>
    private val fragments: Array<Fragment?>

    init {
        TITLES = context.resources.getStringArray(R.array.module_pictures_sections)
        fragments = arrayOfNulls(TITLES.size)
    }


    override fun getItem(position: Int): Fragment? {
        if (fragments[position] == null) {
            when (position) {
                0 -> fragments[position] = BoringFragment.newInstance()
                1 -> fragments[position] = BoringHotFragment.newInstance()
                2 -> fragments[position] = HandyFragment.newInstance()
                3 -> fragments[position] = HandyHotFragment.newInstance()
            }
        }
        return fragments[position]
    }


    override fun getCount(): Int {
        return TITLES.size
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return TITLES[position]
    }
}

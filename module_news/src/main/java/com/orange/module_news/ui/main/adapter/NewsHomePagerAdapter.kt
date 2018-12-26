package com.orange.module_news.ui.main.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.orange.module_news.R
import com.orange.module_news.ui.cnbeta.NewsCnBetaFragment
import com.orange.module_news.ui.jiandan.NewsJianDanFragment


/**
 * @author OrangeHao
 * @date 2018/12/17
 * @Github https://github.com/OrangeHao
 * @describe
 */
class NewsHomePagerAdapter(fm: FragmentManager, context: Context) : FragmentPagerAdapter(fm) {

    private val TITLES: Array<String>
    private val fragments: Array<Fragment?>

    init {
        TITLES = context.resources.getStringArray(R.array.module_news_sections)
        fragments = arrayOfNulls(TITLES.size)
    }


    override fun getItem(position: Int): Fragment? {
        if (fragments[position] == null) {
            when (position) {
                0 -> fragments[position] = NewsCnBetaFragment.newInstance()
                1 -> fragments[position] = NewsJianDanFragment.newInstance()
                else -> {
                }
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

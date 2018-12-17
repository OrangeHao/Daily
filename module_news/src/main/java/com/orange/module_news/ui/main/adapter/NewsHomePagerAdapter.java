package com.orange.module_news.ui.main.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.orange.module_news.R;
import com.orange.module_news.ui.cnbeta.NewsCnBetaFragment;
import com.orange.module_news.ui.jiandan.NewsJianDanFragment;


/**
 * @author OrangeHao
 * @date 2018/12/17
 * @Github https://github.com/OrangeHao
 * @describe
 */
public class NewsHomePagerAdapter extends FragmentPagerAdapter {

  private final String[] TITLES;
  private Fragment[] fragments;

  public NewsHomePagerAdapter(FragmentManager fm, Context context) {
    super(fm);
    TITLES = context.getResources().getStringArray(R.array.module_news_sections);
    fragments = new Fragment[TITLES.length];
  }


  @Override
  public Fragment getItem(int position) {
    if (fragments[position] == null) {
      switch (position) {
        case 0:
          fragments[position] = NewsCnBetaFragment.newInstance();
          break;
        case 1:
          fragments[position] = NewsJianDanFragment.newInstance();
          break;
        default:
          break;
      }
    }
    return fragments[position];
  }


  @Override
  public int getCount() {
    return TITLES.length;
  }


  @Override
  public CharSequence getPageTitle(int position) {
    return TITLES[position];
  }
}

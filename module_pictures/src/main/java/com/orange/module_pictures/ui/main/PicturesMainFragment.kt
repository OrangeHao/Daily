package com.orange.module_pictures.ui.main


import com.alibaba.android.arouter.facade.annotation.Route
import com.orange.module_base.base.BaseFragment
import com.orange.module_base.constants.ARouterPaths

import com.orange.module_pictures.R
import kotlinx.android.synthetic.main.module_pictures_fragment_pictures_main.*


/**
 * @author OrangeHao
 * @date 2018/12/14
 * @Github https://github.com/OrangeHao
 * @describe
 */
@Route(path = ARouterPaths.FRAGMENT_PicturesMainFragment)
class PicturesMainFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.module_pictures_fragment_pictures_main


    override fun initView() {
        super.initView()

        var mAdapter = PictureHomePagerAdapter(childFragmentManager,context!!)
        view_pager.setOffscreenPageLimit(5)
        view_pager.setAdapter(mAdapter)
        sliding_tabs.setViewPager(view_pager)
        view_pager.setCurrentItem(0)
    }

}

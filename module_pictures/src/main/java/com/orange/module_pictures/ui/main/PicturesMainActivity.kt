package com.orange.module_pictures.ui.main

import com.alibaba.android.arouter.facade.annotation.Route
import com.orange.module_base.base.BaseActivity
import com.orange.module_base.constants.ARouterPaths
import com.orange.module_pictures.R

/**
 * @author OrangeHao
 * @date 2018/12/15
 * @Github https://github.com/OrangeHao
 * @describe
 */
class PicturesMainActivity : BaseActivity() {
    override fun getContentLayoutId(): Int = R.layout.module_pictures_activity_pictures_main


    override fun initView() {
        super.initView()
        val transition=supportFragmentManager.beginTransaction()
        transition.add(R.id.root_layout,PicturesMainFragment())
        transition.commit()
    }
}

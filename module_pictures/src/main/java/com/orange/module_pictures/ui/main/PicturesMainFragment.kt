package com.orange.module_pictures.ui.main


import com.alibaba.android.arouter.facade.annotation.Route
import com.orange.module_base.base.BaseFragment
import com.orange.module_base.constants.ARouterPaths

import com.orange.module_pictures.R


/**
 * @author OrangeHao
 * @date 2018/12/14
 * @Github https://github.com/OrangeHao
 * @describe
 */
@Route(path = ARouterPaths.FRAGMENT_PicturesMainFragment)
class PicturesMainFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.module_pictures_fragment_pictures_main


}

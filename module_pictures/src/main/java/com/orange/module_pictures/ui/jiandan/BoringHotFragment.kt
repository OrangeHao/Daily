package com.orange.module_pictures.ui.jiandan


import android.os.Bundle
import android.support.v4.app.Fragment
import com.orange.module_base.base.BaseLazyFragment

import com.orange.module_pictures.R


/**
 * @author OrangeHao
 * @date 2018/12/26
 * @Github https://github.com/OrangeHao
 * @describe
 */
class BoringHotFragment : BaseLazyFragment() {


    override fun getLayoutId(): Int = R.layout.module_pictures_fragment_boring_hot


    companion object {
        @JvmStatic
        fun newInstance() =
                BoringHotFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }


    override fun fetchData() {

    }


}

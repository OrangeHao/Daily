package com.orange.module_pictures.ui.jiandan


import android.os.Bundle
import com.orange.module_base.base.BaseLazyFragment

import com.orange.module_pictures.R


/**
 * @author OrangeHao
 * @date 2018/12/26
 * @Github https://github.com/OrangeHao
 * @describe
 */
class BoringFragment : BaseLazyFragment() {

    override fun getLayoutId(): Int =R.layout.module_pictures_fragment_boring

    override fun fetchData() {
    }


    companion object {
        @JvmStatic
        fun newInstance() =
                BoringFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}

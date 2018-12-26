package com.orange.module_pictures.ui.jiandan

import android.os.Bundle
import com.orange.module_base.base.BaseMvpLazyFragment

import com.orange.module_pictures.R
import com.orange.module_pictures.ui.jiandan.mvp.HandyPicturesPresenter
import com.orange.module_pictures.ui.jiandan.mvp.HandyPicturesView


/**
 * @author OrangeHao
 * @date 2018/12/26
 * @Github https://github.com/OrangeHao
 * @describe
 */
class HandyFragment : BaseMvpLazyFragment<HandyPicturesPresenter>(),HandyPicturesView {

    override fun getLayoutId(): Int = R.layout.module_pictures_fragment_handy


        companion object {
            @JvmStatic
            fun newInstance() =
                    HandyFragment().apply {
                        arguments = Bundle().apply {

                        }
                    }
        }


        override fun fetchData() {

        }


    override fun createPresenter(): HandyPicturesPresenter? {
        return HandyPicturesPresenter(context!!)
    }



    override fun getRefreshDatas(data: List<String>) {

    }

    override fun getLoadMoreDatas(data: List<String>) {
    }

    override fun onFailed(e: Throwable) {
    }

}

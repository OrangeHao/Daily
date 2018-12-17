package com.orange.module_news.ui.cnbeta

import android.os.Bundle
import com.orange.module_base.base.BaseFragment
import com.orange.module_news.R


class NewsCnBetaFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.module_news_fragment_news_cn_beta

    companion object {
        @JvmStatic
        fun newInstance() =
                NewsCnBetaFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}

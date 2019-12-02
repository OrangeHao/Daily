package com.orange.module_news.ui.cnbeta

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.View
import com.orange.module_base.base.BaseSwipBackActivity
import com.orange.module_base.utils.statusbar.setStatusBarLightMode
import com.orange.module_base.utils.statusbar.setTransParentStatusBar
import com.orange.module_news.R
import com.orange.module_news.model.CnbetaNewsDetail
import com.orange.module_news.network.NewsApiRepository
import com.zzhoujay.richtext.RichText
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.module_news_activity_cnbeta_news_detail.*

class CnbetaNewsDetailActivity : BaseSwipBackActivity() {
    override fun getContentLayoutId(): Int =R.layout.module_news_activity_cnbeta_news_detail


    companion object {
        @JvmStatic
        fun start(context: Context, id: String) {
            val starter = Intent(context, CnbetaNewsDetailActivity::class.java)
            starter.putExtra("id", id)
            context.startActivity(starter)
        }
    }


    override fun initView() {
        super.initView()
        setTransParentStatusBar()
        setStatusBarLightMode()

        RichText.initCacheDir(this)

        getNewsDetail(intent.getStringExtra("id"))
    }

    private fun getNewsDetail(id:String){
        loading_progress.visibility= View.VISIBLE
        NewsApiRepository.getInstance()!!.getCnBetaNewsContent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<CnbetaNewsDetail> {
                    override fun onSuccess(t: CnbetaNewsDetail) {
                        setNewsData(t)
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                    }

                })
    }


    private fun setNewsData(data:CnbetaNewsDetail){

        tv_title.text=data.result.title

        RichText.from(htmlFilter(data.result.hometext))
                .into(tv_shortDesc)

        RichText.from(data.result.bodytext)
                .done {
                    loading_progress.visibility= View.GONE
                }
                .into(contentView)
    }

    private val filter = "<p.*?>"//"<strong>", "</strong>"

    fun htmlFilter(htmlTxt: String): String? {
        var htmlTxt = htmlTxt
        if (TextUtils.isEmpty(htmlTxt)) {
            return htmlTxt
        }
        htmlTxt = htmlTxt.replace(filter.toRegex(), "")
        htmlTxt = htmlTxt.replace("<br/></p>|<br></p>|</p>".toRegex(), "<br>")
        htmlTxt = htmlTxt.trim { it <= ' ' }
        if (htmlTxt.endsWith("<br>")) {
            htmlTxt = htmlTxt.substring(0, htmlTxt.length - 4)
        }
        return htmlTxt
    }
}

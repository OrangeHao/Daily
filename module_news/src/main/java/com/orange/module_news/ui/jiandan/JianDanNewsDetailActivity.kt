package com.orange.module_news.ui.jiandan

import android.content.Context
import android.content.Intent
import android.view.View
import com.orange.module_base.base.BaseActivity
import com.orange.module_news.R
import com.orange.module_news.model.NewsDetail
import com.orange.module_news.network.NewsApiRepository
import com.zzhoujay.richtext.RichText
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.module_news_activity_jian_dan_news_detail.*

class JianDanNewsDetailActivity : BaseActivity() {
    override fun getContentLayoutId(): Int =R.layout.module_news_activity_jian_dan_news_detail


    companion object {
        @JvmStatic
        fun start(context: Context, id: Int) {
            val starter = Intent(context, JianDanNewsDetailActivity::class.java)
            starter.putExtra("id", id)
            context.startActivity(starter)
        }
    }


    override fun initView() {
        super.initView()
        RichText.initCacheDir(this)

        getNewsDetail(intent.getIntExtra("id",1))
    }

    private fun getNewsDetail(id:Int){
        loading_progress.visibility= View.VISIBLE
        NewsApiRepository.getInstance()!!.getNewsDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<NewsDetail.PostBean> {
                    override fun onSuccess(t: NewsDetail.PostBean) {
                        RichText.from(t.content)
                                .done {
                                    loading_progress.visibility= View.GONE
                                }
                                .into(contentView)

                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                    }

                })
    }
}

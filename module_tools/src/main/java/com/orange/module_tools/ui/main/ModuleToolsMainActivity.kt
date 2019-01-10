package com.orange.module_tools.ui.main

import com.alibaba.android.arouter.facade.annotation.Route
import com.orange.module_base.base.BaseActivity
import com.orange.module_base.constants.ARouterPaths
import com.orange.module_tools.R
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.module_tools_activity_module_tools_main.*
import pl.droidsonroids.gif.GifDrawable
import java.io.BufferedInputStream
import java.net.URL
import kotlin.concurrent.thread

@Route(path = ARouterPaths.ACTIVITY_ModuleToolsMainActivity)
class ModuleToolsMainActivity : BaseActivity() {
    override fun getContentLayoutId(): Int =R.layout.module_tools_activity_module_tools_main

    private val testUrl="http://wx3.sinaimg.cn/mw1024/729ea5f2gy1fz0g62rgbqg208c0b44qv.gif"
    private val testUrl2="http://wx4.sinaimg.cn/mw1024/6022a700ly1fyzmzu4nzdg20hs09z7wm.gif"

    override fun initView() {
        super.initView()

        Logger.t("czh").d("set bitmap")

        Thread(Runnable { kotlin.run {
            Logger.t("czh").d("run")
            val urlConnection=URL(testUrl).openConnection()
            urlConnection.connect()
            val length=urlConnection.contentLength
            Logger.t("czh").d("$length")
            val inputStream=urlConnection.getInputStream()
            val bufferedInputStream=BufferedInputStream(inputStream,length)
            val gifDrawable=GifDrawable(bufferedInputStream)

            runOnUiThread {
                gifTextureView.setImageDrawable(gifDrawable)
            }
        } }).start()

        Thread(object :Runnable{
            override fun run() {

            }

        }).start()

    }


}

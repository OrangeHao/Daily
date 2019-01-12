package com.orange.module_tools.ui.main

import android.graphics.Bitmap
import android.graphics.Canvas
import com.alibaba.android.arouter.facade.annotation.Route
import com.orange.module_base.base.BaseActivity
import com.orange.module_base.constants.ARouterPaths
import com.orange.module_tools.R
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.module_tools_activity_module_tools_main.*
import pl.droidsonroids.gif.GifDecoder
import pl.droidsonroids.gif.GifDrawable
import pl.droidsonroids.gif.InputSource
import java.net.URL
import java.nio.ByteBuffer
import java.nio.channels.Channels
import android.graphics.Movie
import android.R.attr.bitmap
import android.util.Log
import java.io.BufferedInputStream


@Route(path = ARouterPaths.ACTIVITY_ModuleToolsMainActivity)
class ModuleToolsMainActivity : BaseActivity() {
    override fun getContentLayoutId(): Int =R.layout.module_tools_activity_module_tools_main

    private val testUrl="http://wx3.sinaimg.cn/mw1024/729ea5f2gy1fz0g62rgbqg208c0b44qv.gif"
    private val testUrl2="http://wx4.sinaimg.cn/mw1024/6022a700ly1fyzmzu4nzdg20hs09z7wm.gif"

    override fun initView() {
        super.initView()

        Logger.t("czh").d("set bitmap")

        Thread(Runnable { kotlin.run {
//            Logger.t("czh").d("run")
//            val urlConnection=URL(testUrl).openConnection()
//            urlConnection.connect()
//            val length=urlConnection.contentLength
//            Logger.t("czh").d("$length")
//            val inputStream=urlConnection.getInputStream()
//            val bufferedInputStream=BufferedInputStream(inputStream,length)
//            val gifDrawable=GifDrawable(bufferedInputStream)



            val urlConnection = URL(testUrl).openConnection()
            urlConnection.connect()
            val bufferedInputStream= BufferedInputStream(urlConnection.getInputStream())

//            val channel = Channels.newChannel(urlConnection.getInputStream())
//            while (buffer.remaining() > 0)
//                channel.read(buffer)
//            channel.close()
//
//            runOnUiThread {
//                gifTextureView.setInputSource(InputSource.DirectByteBufferSource(buffer))
//            }
//



//            val start=System.currentTimeMillis()
//            val decoder=GifDecoder(InputSource.InputStreamSource(bufferedInputStream))
//            Log.d("czh", "decoder time:"+(System.currentTimeMillis()-start)/1000)
//            val frame = Bitmap.createBitmap(decoder.width, decoder.height, Bitmap.Config.ARGB_8888)
//            decoder.seekToFrame(0, frame)
//            decoder.recycle()
//            Logger.t("czh").d("bitmap size:"+frame.byteCount/1024+"kb")
//            runOnUiThread {
//                gifimageview.setImageBitmap(frame)
//            }


            /**movie**/
            val start=System.currentTimeMillis()
            val movie = Movie.decodeStream(urlConnection.getInputStream())
            Log.d("czh", "count time:"+(System.currentTimeMillis()-start)/1000)
            val bitmap = Bitmap.createBitmap(movie.width(), movie.height(), Bitmap.Config.ARGB_8888)
            val count = bitmap.getByteCount()
            Log.d("czh", "count:$count")
            var canva= Canvas(bitmap)
            movie.draw(canva, 0f, 0f)
            runOnUiThread {
                    gifimageview.setImageBitmap(bitmap)
            }


        } }).start()

    }


}

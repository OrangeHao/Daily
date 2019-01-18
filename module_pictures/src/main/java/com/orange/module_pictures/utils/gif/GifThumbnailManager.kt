package com.orange.module_pictures.utils.gif

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Movie
import android.util.Log
import android.util.LruCache
import android.widget.ImageView
import com.silencedut.taskscheduler.Task
import com.silencedut.taskscheduler.TaskScheduler
import java.net.URL
import java.util.concurrent.LinkedBlockingQueue

/**
 * created by czh on 2019/1/14
 */
class GifThumbnailManager {


    private var mMemoryCache: LruCache<String, Bitmap>

    private var mDownloadingList:ArrayList<String>
    private var mWaitingList:LinkedBlockingQueue<Map<String,ImageView?>>

    private val DOWNLOADING_NUM_LIMIT=8

    init {
        val maxSize = Runtime.getRuntime().maxMemory() / 1024
        val cacheSize = maxSize / 8
        mMemoryCache = LruCache(cacheSize.toInt())
        mDownloadingList= ArrayList()
        mWaitingList= LinkedBlockingQueue()
    }


    companion object {

        private var mInstance: GifThumbnailManager? = null

        @JvmStatic
        fun getInstance(): GifThumbnailManager {
            if (mInstance == null) {
                synchronized(GifThumbnailManager.javaClass) {
                    if (mInstance == null) {
                        mInstance = GifThumbnailManager()
                    }
                }
            }
            return mInstance!!
        }
    }

    fun loadGifCover(gifUrl: String, imageView: ImageView?) {

        val target = mMemoryCache.get(gifUrl)
        if (target != null) {
            imageView?.setImageBitmap(target)
            return
        }
        imageView?.setImageBitmap(null)

        if (mDownloadingList.contains(gifUrl)){
            return
        }

        if (mDownloadingList.size>DOWNLOADING_NUM_LIMIT){
            Log.d("czh","mWaitingList size:"+mWaitingList.size)
            Log.d("czh","mWaitingList add:"+gifUrl)
            if (!mWaitingList.contains(mapOf(gifUrl to imageView))){
                mWaitingList.put(mapOf(gifUrl to imageView))
            }
            return
        }

        mDownloadingList.add(gifUrl)
        val task = object : Task<Bitmap>() {

            override fun onSuccess(result: Bitmap?) {
                mMemoryCache.put(gifUrl, result)
                mDownloadingList.remove(gifUrl)
                if (gifUrl.equals(imageView?.tag)) {
                    imageView?.setImageBitmap(result)
                }
                if (mWaitingList.isNotEmpty()){
                    val map=mWaitingList.remove()
                    val url=map.keys.elementAt(0)
                    Log.d("czh","load from waitinglist:"+url)
                    loadGifCover(url,map.get(url))
                }
                Log.d("czh","mWaitingList/mDownloadingList:"+mWaitingList.size+"/"+mDownloadingList.size)
            }

            override fun doInBackground(): Bitmap {
                val urlConnection = URL(gifUrl).openConnection()
                urlConnection.connect()
                val movie = Movie.decodeStream(urlConnection.getInputStream())
                val bitmap = Bitmap.createBitmap(movie.width(), movie.height(), Bitmap.Config.ARGB_8888)
                var canva = Canvas(bitmap)
                movie.draw(canva, 0f, 0f)
                return bitmap
            }
        }
        TaskScheduler.execute(task)
    }


}
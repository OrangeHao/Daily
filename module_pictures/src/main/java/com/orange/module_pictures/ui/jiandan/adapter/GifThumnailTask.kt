package com.orange.module_pictures.ui.jiandan.adapter

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Movie
import android.util.Log
import android.widget.ImageView
import com.silencedut.taskscheduler.Task
import java.net.URL

/**
 * created by czh on 2019/1/12
 */
class GifThumnailTask(val gifUrl:String,val imageView:ImageView): Task<Bitmap>() {

    override fun onSuccess(result: Bitmap?) {
        imageView.setImageBitmap(result)
    }

    override fun doInBackground(): Bitmap {
        val urlConnection = URL(gifUrl).openConnection()
        urlConnection.connect()
        val movie = Movie.decodeStream(urlConnection.getInputStream())
        val bitmap = Bitmap.createBitmap(movie.width(), movie.height(), Bitmap.Config.ARGB_8888)
        var canva= Canvas(bitmap)
        movie.draw(canva, 0f, 0f)
        return bitmap
    }

}
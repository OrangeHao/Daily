package com.orange.module_tools.ui.main;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Movie;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * created by czh on 2019/1/10
 */
public class GifHelper {


    public static Bitmap loadGifFirstBitmap(String url) {
        Bitmap bitmap = null;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
            InputStream is = urlConnection.getInputStream();
            Movie movie = Movie.decodeStream(is);
            //Bitmap.Config.ARGB_8888 这里是核心，如果出现图片显示不正确，就换编码试试
            bitmap = Bitmap.createBitmap(movie.width(), movie.height(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            movie.draw(canvas, 0, 0);
            canvas.save();
        } catch (IOException e) {
            Logger.t("czh").d(e);
            e.printStackTrace();
        } finally {
            return bitmap;
        }
    }
}

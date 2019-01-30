package com.orange.module_base.utils.glide;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.orange.module_base.R;
import com.orange.module_base.utils.FileSizeUtil;

import java.io.File;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * created by czh on 2018/4/26
 * 图片加载库封装
 */
public class ImageLoaderWrapper {


    public static int Default_holder=R.color.colorPrimaryLight;

    /******************************* 一般方法 ***********************************/

    public static void loadImgDefault(Context context, String url, ImageView imageView){
        GlideApp.with(context)
                .load(url)
                .transition(withCrossFade())
                .into(imageView);
    }

    public static void loadImgDefault(Context context, String url, int placeholder, ImageView imageView){
        GlideApp.with(context)
                .load(url)
                .transition(withCrossFade())
                .placeholder(placeholder)
                .into(imageView);
    }

    public static void loadImgDefault(Context context, File file, int placeholder, ImageView imageView){
        GlideApp.with(context)
                .load(file)
                .transition(withCrossFade())
                .placeholder(placeholder)
                .into(imageView);
    }

    public static void loadImgDefault(Context context, Uri uri, int placeholder, ImageView imageView){
        GlideApp.with(context).load(uri)
                .transition(withCrossFade())
                .placeholder(placeholder)
                .into(imageView);
    }


    /******************************* 缓存相关方法 ***********************************/

    public static void loadImgDefaultResultCache(Context context, String url, ImageView imageView){
        GlideApp.with(context).load(url)
                .transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);
    }

    public static void loadImgDefaultResultCache(Context context, String url, int placeholder, ImageView imageView){
        GlideApp.with(context).load(url)
                .transition(withCrossFade())
                .placeholder(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);
    }

    public static void loadImgDefaultAllCache(Context context, String url, ImageView imageView){
        GlideApp.with(context).load(url)
                .transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void loadImgDefaultAllCache(Context context, String url, int placeholder, ImageView imageView){
        GlideApp.with(context).load(url)
                .transition(withCrossFade())
                .placeholder(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }


    public static void loadImgAdvWithCache(Context context, String url, ImageView imageView){
        GlideApp.with(context).load(url)
                .transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void loadImgAsGif(Context context, String url, ImageView imageView){
        GlideApp.with(context)
                .asGif()
                .load(url)
                .priority(Priority.IMMEDIATE)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);
    }



    /********************************* 配合RoundimageView使用相关方法  ****************************************/


    public static void loadImgWithAsBitmap(Context context, String url, int placeholder, ImageView imageView) {
        GlideApp.with(context)
                .asBitmap()
                .load(url)
                .placeholder(placeholder)
                .into(imageView);

    }

    public static void loadImgWithAsBitmap(Context context, Uri url, int placeholder, ImageView imageView){
        GlideApp.with(context)
                .asBitmap()
                .load(url)
                .placeholder(placeholder)
                .into(imageView);
    }

    public static void loadImgWithRoundImageAndCache(Context context, String url, int placeholder, ImageView imageView){
        GlideApp.with(context)
                .asBitmap()
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(placeholder)
                .into(imageView);
    }
    public static void loadImgWithRoundImageNoCache(Context context, String url, int placeholder, ImageView imageView){
        GlideApp.with(context)
                .asBitmap()
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache( true )//跳过内存缓存
                .placeholder(placeholder)
                .into(imageView);
    }
    public static void loadImgWithRoundImageNoCache(Context context, Uri url, int placeholder, ImageView imageView){
        GlideApp.with(context)
                .asBitmap()
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache( true )//跳过内存缓存
                .placeholder(placeholder)
                .into(imageView);
    }



    public static void loadImgWithAsBitmap(Context context, String url, ImageView imageView){
        GlideApp.with(context)
                .asBitmap()
                .placeholder(Default_holder)
                .load(url)
                .into(imageView);
    }

    public static void loadImgWithRoundImageAndCache(Context context, String url, ImageView imageView){
        GlideApp.with(context)
                .asBitmap()
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);
    }



    /******************************* target相关方法 *********************************************/

    public static void loadImgIntoTarget(Context context, int drawableId, Transformation<Bitmap> transformation, Target target){
        GlideApp.with(context)
                .load(drawableId)
                .transform(transformation)
                .into(target);
    }

    public static void loadImgIntoTarget(Context context, int drawableId, RequestOptions options, Target target){
        GlideApp.with(context)
                .load(drawableId)
                .apply(options)
                .into(target);
    }

    public static void loadImgWithOption(Context context, String url, RequestOptions options, RequestListener listener, ImageView imageView){
        GlideApp.with(context)
                .load(url)
                .apply(options)
                .listener(listener)
                .into(imageView);
    }
    /******************************* 下载相关方法 *********************************************/


    public static void downloadImg(Context context, String url, final ImageDownloadListner listner){
        GlideApp.with(context)
                .asBitmap()
                .load(url)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        if (listner!=null){
                            listner.downloadFinish(resource);
                        }
                    }
                });
    }

    public interface ImageDownloadListner{
        /**
         *
         * @param bitmap
         */
        void downloadFinish(Bitmap bitmap);
    }



    /************************************ setting相关方法 *********************************************/

    /**
     * 清除缓存
     * @param context
     */
    public static void clearCache( final Context context ){
        clearMemoryCache( context );
        new Thread(new Runnable() {
            @Override
            public void run() {
                clearDiskCache(  context );
            }
        }).start();
    }

    /**
     * 清除内存缓存
     * @param context
     */
    public static void clearMemoryCache( Context context ){
        Glide.get( context ).clearMemory();
    }

    /**
     * 清除磁盘缓存
     * @param context
     */
    public static void clearDiskCache( Context context ){
        Glide.get( context ).clearDiskCache();
    }


    /**
     * 获取缓存文件夹大小
     * @param context
     * @return
     */
    public static String getCacheFileSize(Context context){
        return FileSizeUtil.INSTANCE.getAutoFileOrFilesSize(Glide.getPhotoCacheDir(context,"PictureCache"));
    }

    public static int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

}

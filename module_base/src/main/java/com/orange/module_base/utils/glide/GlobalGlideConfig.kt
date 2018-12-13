package com.orange.module_base.utils.glide

import android.content.Context

import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule

/**
 * @author OrangeHao
 * @date 2018/12/13
 * @Github https://github.com/OrangeHao
 * @describe
 */
@GlideModule
class GlobalGlideConfig : AppGlideModule() {

    companion object {
        val CACHE_FOLDER_NAME="PictureCache"
    }

    override fun isManifestParsingEnabled(): Boolean {
        //return super.isManifestParsingEnabled();
        return false
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val diskCacheSizeBytes = 1024 * 1024 * 500  //500 MB
        builder.setDiskCache(
                InternalCacheDiskCacheFactory(context, CACHE_FOLDER_NAME, diskCacheSizeBytes.toLong()))
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
    }
}

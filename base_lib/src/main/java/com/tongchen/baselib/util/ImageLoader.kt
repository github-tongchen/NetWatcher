package com.tongchen.baselib.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * @author TongChen
 * @date 2020/11/20  14:31
 * <p>
 * Desc:
 */
fun ImageView.loadSmallImage(url: String) {
    loadSmallImage(url, DiskCacheStrategy.RESOURCE)
}

fun ImageView.loadSmallImage(url: String, strategy: DiskCacheStrategy) {
    loadImage(url, strategy)
}


fun ImageView.loadMiddleImage(url: String) {
    loadMiddleImage(url, DiskCacheStrategy.RESOURCE)
}

fun ImageView.loadMiddleImage(url: String, strategy: DiskCacheStrategy) {
    loadImage(url, strategy)
}


fun ImageView.loadLargeImage(url: String) {
    loadLargeImage(url, DiskCacheStrategy.DATA)
}

fun ImageView.loadLargeImage(url: String, strategy: DiskCacheStrategy) {
    loadImage(url, strategy)
}


fun ImageView.loadImage(url: String, strategy: DiskCacheStrategy) {
    Glide.with(this.context).load(url).diskCacheStrategy(strategy).into(this)
}
package com.tongchen.basemodule.databinding.adapter

import android.graphics.drawable.Drawable
import android.widget.ImageView

import androidx.databinding.BindingAdapter

/**
 * @author TongChen
 * @date 2019/01/09  17:40
 * <p>
 * Desc:
 */

@BindingAdapter(value = ["imageUrl"])
fun ImageView.loadImage(url: String) {
    //PicassoHelperUtils.displayImage(url, this)
}

@BindingAdapter(value = ["imageUrl", "imageError"])
fun ImageView.loadImage(url: String, error: Drawable) {
    //PicassoHelperUtils.displayImage(url, this, error)
}

@BindingAdapter(value = ["imageUrl", "imageError", "imageWidth", "imageHeight", "imageCenterCrop"], requireAll = false)
fun ImageView.loadImage(url: String, error: Drawable, width: Int, height: Int, centerCrop: Boolean) {
    //PicassoHelperUtils.displayImage(this, url, error, width, height, centerCrop)
}

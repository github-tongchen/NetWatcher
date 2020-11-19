package com.tongchen.basemodule.databinding.adapter

import android.app.Activity
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.widget.ImageView

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * @author TongChen
 * @date 2019/01/09  17:40
 * <p>
 * Desc:
 */

@BindingAdapter(value = ["imageUrl"])
fun ImageView.loadImage(url: String) {
    //PicassoHelperUtils.displayImage(url, this)
    Glide.with(this.context).load(url).into(this)
}

@BindingAdapter(value = ["imageUrl"])
fun ImageView.loadImage(urls: List<String>) {
    for (value in urls) {
        if (value.isBlank()) {
            continue
        }
        Glide.with(this.context).load(value).into(this)
        break
    }
}

/*@BindingAdapter(value = ["imageUrl", "imageError"])
fun ImageView.loadImage(url: String, error: Drawable) {
    //PicassoHelperUtils.displayImage(url, this, error)
//    Glide.with()
}

@BindingAdapter(value = ["imageUrl", "imageError", "imageWidth", "imageHeight", "imageCenterCrop"], requireAll = false)
fun ImageView.loadImage(url: String, error: Drawable, width: Int, height: Int, centerCrop: Boolean) {
    //PicassoHelperUtils.displayImage(this, url, error, width, height, centerCrop)
}*/

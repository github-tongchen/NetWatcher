package com.tongchen.basemodule.databinding.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.tongchen.baselib.util.loadLargeImage
import com.tongchen.baselib.util.loadMiddleImage

/**
 * @author TongChen
 * @date 2019/01/09  17:40
 * <p>
 * Desc:
 */

@BindingAdapter(value = ["middleImgUrl"])
fun ImageView.loadMiddleImage(url: String) {
    this.loadMiddleImage(url)
}

@BindingAdapter(value = ["middleImgUrls"])
fun ImageView.loadMiddleImage(urls: List<String>) {
    for (url in urls) {
        if (url.isBlank()) {
            continue
        }
        this.loadMiddleImage(url)
        break
    }
}

@BindingAdapter(value = ["largeImgUrl"])
fun ImageView.loadLargeImage(url: String) {
    this.loadLargeImage(url)
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

package com.tongchen.gank.biz.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @author TongChen
 * @date 2019/10/27  17:54
 * <p>
 * Desc: @Parcelize 注解目前为Kotlin extension实验性功能
 */
@Parcelize
data class GankResult constructor(

    @SerializedName("views")
    var views: Int = 0,

    @SerializedName("used")
    var isUsed: Boolean = false,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("type")
    var type: String? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("stars")
    var stars: Int = 0,

    @SerializedName("publishedAt")
    var publishedAt: String? = null,

    @SerializedName("likeCounts")
    var likeCounts: Int = 0,

    @SerializedName("images")
    var images: List<String>? = null,

    @SerializedName("desc")
    var desc: String? = null,

    @SerializedName("createdAt")
    var createdAt: String? = null,

    @SerializedName("category")
    var category: String? = null,

    @SerializedName("author")
    var author: String? = null,

    @SerializedName("_id")
    var id: String? = null

) : Parcelable {

}

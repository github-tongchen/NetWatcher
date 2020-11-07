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

    @SerializedName("createdAt")
    var createdAt: String? = null,

    @SerializedName("desc")
    var desc: String? = null,

    //  Video和Welfare没有images字段，要做null处理
    @SerializedName("images")
    var images: List<String>? = null,

    @SerializedName("publishedAt")
    var publishedAt: String? = null,

    @SerializedName("source")
    var source: String? = null,

    @SerializedName("type")
    var type: String? = null,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("used")
    var isUsed: Boolean = false,

    @SerializedName("who")
    var who: String? = null,

    @SerializedName("_id")
    var id: String? = null

) : Parcelable {

}

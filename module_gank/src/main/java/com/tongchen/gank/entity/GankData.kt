package com.tongchen.gank.entity

import com.google.gson.annotations.SerializedName


/**
 * @author TongChen
 * @date 2019/10/27  17:54
 * <p>
 * Desc: GSON使用方式参考：http://www.jianshu.com/p/e740196225a4
 */
class GankData<T> {

    @SerializedName("error")
    var error: Boolean? = null

    @SerializedName("results")
    var result: T? = null
}

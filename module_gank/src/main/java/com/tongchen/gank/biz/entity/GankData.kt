package com.tongchen.gank.biz.entity

import com.google.gson.annotations.SerializedName


/**
 * @author TongChen
 * @date 2019/10/27  17:54
 * <p>
 * Desc: GSON使用方式参考：http://www.jianshu.com/p/e740196225a4
 */
class GankData<T> {

    @SerializedName("error")
    var mError: Boolean = false

    @SerializedName("results")
    var mResult: T? = null
}

package com.tongchen.gank.net

import com.tongchen.baselib.converter.ResponseFormat
import com.tongchen.basemodule.base.BaseApiHelper
import com.tongchen.gank.biz.entity.GankData
import com.tongchen.gank.biz.entity.GankResult

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author TongChen
 * @date 2019/10/29  11:24
 * <p>
 * Desc: 干货集中营 API
 */
interface GankApiHelper : BaseApiHelper {

    @GET("data/category/{category}/type/{type}/page/{page}/count/{count}")
    @ResponseFormat(ResponseFormat.JSON)
    fun getGankDataByPage(
        @Path("category") category: String?,
        @Path("type") type: String,
        @Path("page") page: Int,
        @Path("count") count: Int
    ): Observable<GankData<MutableList<GankResult>>>
}

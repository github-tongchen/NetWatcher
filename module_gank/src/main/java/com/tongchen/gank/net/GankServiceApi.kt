package com.tongchen.gank.net

import com.tongchen.baselib.converter.ResponseFormat
import com.tongchen.gank.entity.GankData
import com.tongchen.gank.entity.GankResult

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author TongChen
 * @date 2019/10/29  11:24
 * <p>
 * Desc: 干货集中营 API
 */
interface GankServiceApi {

    @GET("data/{category}/{size}/{page}")
    @ResponseFormat(ResponseFormat.JSON)
    fun getGankDataByPage(
        @Path("category") category: String,
        @Path("size") size: Int,
        @Path("page") page: Int
    ): Observable<GankData<MutableList<GankResult>>>
}

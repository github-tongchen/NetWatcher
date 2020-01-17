package com.tongchen.mzitu.net

import com.tongchen.baselib.converter.ResponseFormat
import com.tongchen.basemodule.base.BaseApiHelper

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


interface MZiTuApiHelper:BaseApiHelper {


    @Headers("Referer: " + MZiTuApi.DOMAIN_MZITU, "Domain-Name: " + MZiTuApi.DOMAIN_NAME_MZITU)
    @GET("page/{page}/")
    @ResponseFormat(ResponseFormat.STRING)
    fun mZiTuIndex(@Path("page") page: Int): Observable<String>

    @Headers("Referer: " + MZiTuApi.DOMAIN_MZITU, "Domain-Name: " + MZiTuApi.DOMAIN_NAME_MZITU)
    @GET("hot/page/{page}/")
    @ResponseFormat(ResponseFormat.STRING)
    fun mZiTuHot(@Path("page") page: Int): Observable<String>

    @Headers("Referer: " + MZiTuApi.DOMAIN_MZITU, "Domain-Name: " + MZiTuApi.DOMAIN_NAME_MZITU)
    @GET("best/page/{page}/")
    @ResponseFormat(ResponseFormat.STRING)
    fun mZiTuBest(@Path("page") page: Int): Observable<String>

    @Headers("Referer: " + MZiTuApi.DOMAIN_MZITU, "Domain-Name: " + MZiTuApi.DOMAIN_NAME_MZITU)
    @GET("xinggan/page/{page}/")
    @ResponseFormat(ResponseFormat.STRING)
    fun mZiTuSexy(@Path("page") page: Int): Observable<String>

    @Headers("Referer: " + MZiTuApi.DOMAIN_MZITU, "Domain-Name: " + MZiTuApi.DOMAIN_NAME_MZITU)
    @GET("japan/page/{page}/")
    @ResponseFormat(ResponseFormat.STRING)
    fun mZiTuJapan(@Path("page") page: Int): Observable<String>

    @Headers("Referer: " + MZiTuApi.DOMAIN_MZITU, "Domain-Name: " + MZiTuApi.DOMAIN_NAME_MZITU)
    @GET("taiwan/page/{page}/")
    @ResponseFormat(ResponseFormat.STRING)
    fun mZiTuTaiwan(@Path("page") page: Int): Observable<String>

    @Headers("Referer: " + MZiTuApi.DOMAIN_MZITU, "Domain-Name: " + MZiTuApi.DOMAIN_NAME_MZITU)
    @GET("mm/page/{page}/")
    @ResponseFormat(ResponseFormat.STRING)
    fun mZiTuMm(@Path("page") page: Int): Observable<String>

    @Headers("Referer: " + MZiTuApi.DOMAIN_MZITU, "Domain-Name: " + MZiTuApi.DOMAIN_NAME_MZITU)
    @GET("{id}")
    @ResponseFormat(ResponseFormat.STRING)
    fun mZiTuImageList(@Path("id") id: Int): Observable<String>
}

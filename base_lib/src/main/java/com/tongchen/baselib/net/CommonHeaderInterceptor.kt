package com.tongchen.baselib.net

import androidx.annotation.NonNull
import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author TongChen
 * @date 2019/10/29  10:54
 * <p>
 * Desc: 统一设置请求头，解决部分网站的反爬虫问题
 */
class CommonHeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(@NonNull chain: Interceptor.Chain): Response {
        //统一设置请求头
        val original = chain.request()

        val requestBuilder = original.newBuilder()
        requestBuilder.header(
            "User-Agent",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 Edge/16.16299"
        )
        requestBuilder.header("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5")
        requestBuilder.header("Proxy-Connection", "keep-alive")
        requestBuilder.header("Cache-Control", "max-age=0")
        // requestBuilder.header("X-Forwarded-For","114.114.114.117")
        requestBuilder.method(original.method, original.body)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}

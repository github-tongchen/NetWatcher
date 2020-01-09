package com.tongchen.basemodule.di

import android.content.Context
import com.tongchen.baselib.util.LogUtils
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * @author TongChen
 * @date 2019/10/29  11:32
 * <p>
 * Desc:
 */
@Module
class BaseApiServiceModule {

    @Singleton
    @Provides
    fun provideOkHttpClientBuilder(
        context: Context,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()

        val cacheSize = 10 * 1024 * 1024
        val dir = File(context.cacheDir, "Cache")
        val cache = Cache(dir, cacheSize.toLong())
        //  设置缓存
        builder.cache(cache)
        builder.addInterceptor(loggingInterceptor)
        builder.connectTimeout(10, TimeUnit.SECONDS)
        builder.readTimeout(10, TimeUnit.SECONDS)
        builder.writeTimeout(10, TimeUnit.SECONDS)

        return builder
    }


    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                LogUtils.i("RetrofitLog", "retrofitBack = $message")
            }
        })
        return httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }
}
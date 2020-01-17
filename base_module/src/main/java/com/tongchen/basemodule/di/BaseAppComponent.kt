package com.tongchen.basemodule.di

import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * @author TongChen
 * @date 2019/10/27  18:31
 * <p>
 * Desc:
 */
@Singleton
@Component(modules = [BaseAppModule::class])
interface BaseAppComponent {

    /*fun restApi(): RestApi

    fun rxBus(): RxBus*/

    fun provideOkHttpClientBuilder(): OkHttpClient.Builder
}
package com.tongchen.basemodule.di

import android.content.Context
import dagger.Component
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

    fun rxBus(): RxBus

    fun apiService(): HttpApiService*/
}
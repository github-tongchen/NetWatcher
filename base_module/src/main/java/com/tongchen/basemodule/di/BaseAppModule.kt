package com.tongchen.basemodule.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author TongChen
 * @date 2019/10/27  17:54
 * <p>
 * Desc:
 */
@Module(includes = [BaseApiServiceModule::class])
class BaseAppModule constructor(application: Application) {

    private var mApplication: Application = application

    @Singleton
    @Provides
    fun provideApplication(): Application = mApplication

    @Singleton
    @Provides
    fun provideContext(): Context = mApplication


    /*@Singleton
    @Provides
    fun provideRxBus(): RxBus {
        return RxBus.getInstance()
    }*/

}
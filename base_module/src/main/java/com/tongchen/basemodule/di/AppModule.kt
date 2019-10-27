package com.tongchen.basemodule.di

import android.app.Application
import android.content.Context
import android.provider.SyncStateContract
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author TongChen
 * @date 2019/10/27  17:54
 * <p>
 * Desc:
 */
@Module
class AppModule constructor(application: Application) {

    private var mApplication: Application = application

    @Singleton
    @Provides
    fun provideApplication(): Application = mApplication

    @Singleton
    @Provides
    fun provideContext(): Context = mApplication


    @Singleton
    @Provides
    fun provideRxBus(): RxBus {
        return RxBus.getInstance()
    }

    @Singleton
    @Provides
    fun provideHttpApiService(restApi: RestApi): HttpApiService {
        return restApi.retrofitNet(SyncStateContract.Constants.BASE_URL)
            .create(HttpApiService::class.java)
    }
}
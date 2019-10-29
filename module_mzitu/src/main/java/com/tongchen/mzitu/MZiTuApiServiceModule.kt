package com.tongchen.mzitu

import com.tongchen.baselib.http.CommonHeaderInterceptor
import com.tongchen.basemodule.di.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * @author TongChen
 * @date 2019/10/29  13:44
 * <p>
 * Desc:
 */
@Module
class MZiTuApiServiceModule {

    @AppScope
    @Provides
    fun provideMZiTuServiceApi(retrofit: Retrofit): MZiTuServiceApi {
        return retrofit.create(MZiTuServiceApi::class.java)
    }

    @AppScope
    @Provides
    fun providesRetrofit(
        builder: OkHttpClient.Builder,
        commonHeaderInterceptor: CommonHeaderInterceptor
    ): Retrofit {
        builder.addInterceptor(commonHeaderInterceptor)
        return Retrofit.Builder()
            .baseUrl(MZiTuApi.DOMAIN_MZITU)
            .client(builder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(MultipleConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    @AppScope
    @Provides
    fun provideCommonHeaderInterceptor(): CommonHeaderInterceptor {
        return CommonHeaderInterceptor()
    }
}
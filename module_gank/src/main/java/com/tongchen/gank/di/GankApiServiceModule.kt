package com.tongchen.gank.di

import com.tongchen.baselib.net.SSLSocketFactoryCompat
import com.tongchen.basemodule.di.AppScope
import com.tongchen.gank.net.GankApi
import com.tongchen.gank.net.GankServiceApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

/**
 * @author TongChen
 * @date 2019/10/29  13:44
 * <p>
 * Desc:
 */
@Module
class GankApiServiceModule {

    @AppScope
    @Provides
    fun provideGankServiceApi(retrofit: Retrofit): GankServiceApi {
        return retrofit.create(GankServiceApi::class.java)
    }

    @AppScope
    @Provides
    fun providesRetrofit(
        builder: OkHttpClient.Builder,
        sslSocketFactory: SSLSocketFactory,
        trustAllCert: X509TrustManager
    ): Retrofit {
        //  解决https证书过期无法访问的问题
        builder.sslSocketFactory(sslSocketFactory, trustAllCert)
        return Retrofit.Builder()
            .baseUrl(GankApi.DOMAIN_GANK)
            .client(builder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(MultipleConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @AppScope
    @Provides
    fun provideSSLSocketFactory(trustAllCert: X509TrustManager): SSLSocketFactory {
        return SSLSocketFactoryCompat(trustAllCert)
    }

    @AppScope
    @Provides
    fun provideX509TrustManager(): X509TrustManager {
        // 自定义一个信任所有证书的TrustManager
        return object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {

            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {

            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
    }
}
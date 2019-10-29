package com.tongchen.baselib.converter

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jaxb.JaxbConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.lang.reflect.Type

/**
 * @author TongChen
 * @date 2019/10/25  23:09
 * <p>
 * Desc:转换工厂类，支持JSON、XML、STRING 3种格式的工厂创建，配合[ResponseFormat]使用
 */
class MultipleConverterFactory private constructor() : Converter.Factory() {

    private val mJsonFactory: Converter.Factory = GsonConverterFactory.create()
    private val mStringFactory: Converter.Factory = ScalarsConverterFactory.create()
    private val mXmlFactory: Converter.Factory = JaxbConverterFactory.create()

    companion object {
        fun create(): MultipleConverterFactory {
            return MultipleConverterFactory()
        }
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        for (value in annotations) {
            if (value !is ResponseFormat) {
                continue
            }
            when (value.value) {
                ResponseFormat.JSON -> return mJsonFactory.responseBodyConverter(
                    type,
                    annotations,
                    retrofit
                )
                ResponseFormat.STRING -> return mStringFactory.responseBodyConverter(
                    type,
                    annotations,
                    retrofit
                )
                ResponseFormat.XML -> return mXmlFactory.responseBodyConverter(
                    type,
                    annotations,
                    retrofit
                )
            }
        }
        return super.responseBodyConverter(type, annotations, retrofit)
    }

}
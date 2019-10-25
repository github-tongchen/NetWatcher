package com.tongchen.baselib.converter


/**
 * @author TongChen
 * @date 2019/10/25  23:18
 * <p>
 * Desc:转换类型注解，支持JSON、XML、STRING 3种格式的工厂创建，配合[MultipleConverterFactory]使用
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ResponseFormat(val value: String = "") {
    companion object {

        val JSON = "json"

        val STRING = "string"

        val XML = "xml"
    }
}

package com.tongchen.gank.biz.entity

import androidx.annotation.NonNull
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/**
 * @author TongChen
 * @date 2020/11/06  10:39
 * <p>
 * Desc: 重写toString()方法便于输出日志查看
 */
open class BaseBean {

    @NonNull
    @Override
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE)
    }
}
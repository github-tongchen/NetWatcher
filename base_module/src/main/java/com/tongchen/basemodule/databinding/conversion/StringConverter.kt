package com.tongchen.basemodule.databinding.conversion

import android.text.TextUtils
import androidx.databinding.BindingConversion

/**
 * @author TongChen
 * @date 2020/01/10  10:47
 * <p>
 * Desc:
 */

@BindingConversion
fun emptyData(str: String): String {
    if (TextUtils.isEmpty(str) || TextUtils.equals("null", str)) {
        return "空数据"
    }
    return str
}

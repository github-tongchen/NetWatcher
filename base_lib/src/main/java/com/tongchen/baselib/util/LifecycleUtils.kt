package com.tongchen.baselib.util

import androidx.lifecycle.LifecycleOwner
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.AutoDisposeConverter
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider

/**
 * @author TongChen
 * @date 2020/01/15  17:42
 * <p>
 * Desc:
 */
object LifecycleUtils {

    fun <T> bindLifecycle(owner: LifecycleOwner): AutoDisposeConverter<T> {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(owner))
    }
}
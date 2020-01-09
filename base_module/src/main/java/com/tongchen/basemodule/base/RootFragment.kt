package com.tongchen.basemodule.base

import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment

/**
 * @author TongChen
 * @date 2020/01/06  14:48
 * <p>
 * Desc:
 */
abstract class RootFragment : Fragment() {

    val TAG = this.javaClass.simpleName

    @NonNull
    @LayoutRes
    abstract fun getLayoutId(): Int
}
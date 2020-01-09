package com.tongchen.basemodule.base

import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity

/**
 * @author TongChen
 * @date 2020/01/06  14:47
 * <p>
 * Desc:
 */
abstract class RootActivity : AppCompatActivity() {

    @NonNull
    @LayoutRes
    abstract fun getLayoutId(): Int
}
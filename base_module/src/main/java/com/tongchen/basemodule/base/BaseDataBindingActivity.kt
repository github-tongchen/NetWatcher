package com.tongchen.basemodule.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author TongChen
 * @date 2019/11/06  11:23
 * <p>
 * Desc:
 */
abstract class BaseDataBindingActivity<DB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var mDataBinding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
    }

    @NonNull
    @LayoutRes
    abstract fun getLayoutId(): Int
}
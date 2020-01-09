package com.tongchen.basemodule.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author TongChen
 * @date 2019/11/06  11:23
 * <p>
 * Desc:
 */
abstract class BaseActivity<DB : ViewDataBinding> : RootActivity() {

    protected lateinit var mDataBinding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
    }

    override fun onDestroy() {
        mDataBinding.unbind()
        super.onDestroy()
    }

}
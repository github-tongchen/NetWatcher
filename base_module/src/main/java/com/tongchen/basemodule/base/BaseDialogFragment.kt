package com.tongchen.basemodule.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author TongChen
 * @date 2020/11/20  16:51
 * <p>
 * Desc:
 */
abstract class BaseDialogFragment<DB : ViewDataBinding> :RootDialogFragment() {

    protected lateinit var mDataBinding: DB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)

        return mDataBinding.root
    }

    override fun onDestroyView() {
        mDataBinding.unbind()
        super.onDestroyView()
    }
}
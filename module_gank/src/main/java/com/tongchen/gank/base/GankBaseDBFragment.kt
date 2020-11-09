package com.tongchen.gank.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.tongchen.basemodule.base.BaseFragment
import com.tongchen.basemodule.di.BaseViewModule
import com.tongchen.gank.di.*

/**
 * @author TongChen
 * @date 2020/01/17  15:35
 * <p>
 * Desc:
 */
abstract class GankBaseDBFragment<DB : ViewDataBinding> : BaseFragment<DB>() {

    private var mDataBindingComponent: GankDBComponent? = null

    protected fun dataBindingComponent(): GankDBComponent? {
        if (mDataBindingComponent == null) {
            mDataBindingComponent = DaggerGankDBComponent.builder()
                .gankAppComponent(GankDiKit.mComponent as GankAppComponent)
                .baseViewModule(BaseViewModule(this))
                .build()
        }
        return mDataBindingComponent
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inject2Fragment()
    }*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject2Fragment()
    }

    abstract fun inject2Fragment()
}
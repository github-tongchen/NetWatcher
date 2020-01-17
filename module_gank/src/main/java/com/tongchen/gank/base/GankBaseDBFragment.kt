package com.tongchen.gank.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.tongchen.basemodule.base.BaseFragment
import com.tongchen.basemodule.di.BaseViewModule
import com.tongchen.gank.di.DaggerGankDataBindingComponent
import com.tongchen.gank.di.GankAppComponent
import com.tongchen.gank.di.GankDataBindingComponent
import com.tongchen.gank.di.GankDiKit

/**
 * @author TongChen
 * @date 2020/01/17  15:35
 * <p>
 * Desc:
 */
abstract class GankBaseDBFragment<DB : ViewDataBinding> : BaseFragment<DB>() {

    private var mDataBindingComponent: GankDataBindingComponent? = null

    protected fun dataBindingComponent(): GankDataBindingComponent? {
        if (mDataBindingComponent == null) {
            mDataBindingComponent = DaggerGankDataBindingComponent.builder()
                .gankAppComponent(GankDiKit.mComponent as GankAppComponent)
                .baseViewModule(BaseViewModule(this.activity!!))
                .build()
        }
        return mDataBindingComponent
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inject2Fragment()
    }

    abstract fun inject2Fragment()
}
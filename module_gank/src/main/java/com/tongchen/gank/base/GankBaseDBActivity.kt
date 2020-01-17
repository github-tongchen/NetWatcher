package com.tongchen.gank.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.tongchen.basemodule.base.BaseActivity
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
abstract class GankBaseDBActivity<DB : ViewDataBinding> : BaseActivity<DB>() {

    private var mDataBindingComponent: GankDataBindingComponent? = null

    protected fun dataBindingComponent(): GankDataBindingComponent? {
        if (mDataBindingComponent == null) {
            mDataBindingComponent = DaggerGankDataBindingComponent.builder()
                .gankAppComponent(GankDiKit.mComponent as GankAppComponent)
                .baseViewModule(BaseViewModule(this))
                .build()
        }
        return mDataBindingComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject2Activity()
    }

    abstract fun inject2Activity()
}
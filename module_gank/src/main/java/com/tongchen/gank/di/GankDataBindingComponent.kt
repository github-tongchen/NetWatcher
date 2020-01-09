package com.tongchen.gank.di

import androidx.databinding.DataBindingComponent
import com.tongchen.basemodule.di.BaseViewModule
import com.tongchen.basemodule.di.ViewScope
import com.tongchen.gank.ui.GankTextFragment
import dagger.Component

/**
 * @author TongChen
 * @date 2020/01/09  15:41
 * <p>
 * Desc:
 */
@ViewScope
@Component(dependencies = [GankAppComponent::class], modules = [BaseViewModule::class])
interface GankDataBindingComponent : DataBindingComponent {

    fun inject(fragment: GankTextFragment)
}
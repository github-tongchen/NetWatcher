package com.tongchen.mzitu.di

import androidx.databinding.DataBindingComponent
import com.tongchen.basemodule.di.BaseViewModule
import com.tongchen.basemodule.di.ViewScope
import dagger.Component

/**
 * @author TongChen
 * @date 2020/01/09  15:41
 * <p>
 * Desc: 在 DataBinding 页面中使用 Dagger2 有一点不一样的地方，即该页面注入的Component必须继承 DataBindingComponent，否则会注入失败
 */
@ViewScope
@Component(dependencies = [MZiTuAppComponent::class], modules = [BaseViewModule::class])
interface MZiTuBindingComponent : DataBindingComponent {

    //fun inject(fragment: GankTextFragment)
}
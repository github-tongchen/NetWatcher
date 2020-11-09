package com.tongchen.gank.di

import androidx.databinding.DataBindingComponent
import com.tongchen.basemodule.di.BaseViewModule
import com.tongchen.basemodule.di.ViewScope
import com.tongchen.gank.biz.ui.fragment.GankMainFragment
import com.tongchen.gank.biz.ui.fragment.GankTextFragment
import dagger.Component

/**
 * @author TongChen
 * @date 2020/01/09  15:41
 * <p>
 * Desc: 在 DataBinding 页面中使用 Dagger2 有一点不一样的地方，即该页面注入的Component必须继承 DataBindingComponent，否则会注入失败
 */
@ViewScope
@Component(dependencies = [GankAppComponent::class], modules = [BaseViewModule::class])
interface GankDBComponent : DataBindingComponent {

    fun inject(fragment: GankMainFragment)

    fun inject(fragment: GankTextFragment)
}
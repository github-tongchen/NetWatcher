package com.tongchen.gank.di

import com.tongchen.basemodule.di.BaseViewModule
import com.tongchen.basemodule.di.ViewScope
import com.tongchen.gank.biz.ui.fragment.CategoryFragment
import dagger.Component

/**
 * @author TongChen
 * @date 2020/01/09  15:45
 * <p>
 * Desc: inject方法的参数必须写具体的注入类，不能是父类，否则会注入失败
 */
@ViewScope
@Component(dependencies = [GankAppComponent::class], modules = [GankViewModule::class, BaseViewModule::class])
interface GankViewComponent {


    //fun inject(activity Activity)
    fun inject(fragment: CategoryFragment)
}
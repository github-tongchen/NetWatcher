package com.tongchen.gank.di

import com.tongchen.basemodule.di.BaseViewModule
import com.tongchen.basemodule.di.ViewScope
import dagger.Component

/**
 * @author TongChen
 * @date 2020/01/09  15:45
 * <p>
 * Desc:
 */
@ViewScope
@Component(dependencies = [GankAppComponent::class], modules = [BaseViewModule::class])
interface GankActivityComponent {

}
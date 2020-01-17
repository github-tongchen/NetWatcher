package com.tongchen.mzitu.di

import com.tongchen.basemodule.di.BaseViewModule
import com.tongchen.basemodule.di.ViewScope
import com.tongchen.mzitu.di.MZiTuAppComponent
import dagger.Component

/**
 * @author TongChen
 * @date 2020/01/17  17:54
 * <p>
 * Desc:
 */
@ViewScope
@Component(dependencies = [MZiTuAppComponent::class], modules = [BaseViewModule::class])
interface MZiTuViewComponent {

    //fun inject(activity Activity)
    //fun inject(fragment Fragment)
}
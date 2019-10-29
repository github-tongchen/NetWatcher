package com.tongchen.basemodule.di

import android.app.Activity
import dagger.Component

/**
 * @author TongChen
 * @date 2019/10/29  18:05
 * <p>
 * Desc:
 */
@ViewScope
@Component(modules = [BaseViewModule::class])
interface BaseViewComponent {

    fun activity(): Activity

//    fun inject(activity: AbstractActivity)

//    fun inject(fragment: BaseFragment)
}
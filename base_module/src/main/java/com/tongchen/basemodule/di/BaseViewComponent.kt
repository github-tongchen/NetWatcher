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

    //  星号投影
//    fun inject(activity: BaseMvpLceActivity<*, *, *, *>)

//    fun inject(fragment: BaseMvpFragment<*, *, *, *>)
}
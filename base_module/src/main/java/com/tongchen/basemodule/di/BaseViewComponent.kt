package com.tongchen.basemodule.di

import android.app.Activity
import com.tongchen.basemodule.base.BaseMvpActivity
import com.tongchen.basemodule.base.BaseMvpFragment
import com.tongchen.basemodule.base.RootActivity
import com.tongchen.basemodule.base.RootFragment
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

    fun inject(activity: RootActivity)

    fun inject(fragment: RootFragment)

    //  星号投影
    //  fun inject(activity: BaseMvpActivity<*, *, *, *>)
    //  fun inject(fragment: BaseMvpFragment<*, *, *, *>)
}
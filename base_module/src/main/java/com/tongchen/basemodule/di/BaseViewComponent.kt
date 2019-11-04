package com.tongchen.basemodule.di

import android.app.Activity
import com.tongchen.basemodule.BaseMVPActivity
import com.tongchen.basemodule.BaseMVPFragment
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
//    fun inject(activity: BaseMVPActivity<*, *, *, *>)

//    fun inject(fragment: BaseMVPFragment<*, *, *, *>)
}
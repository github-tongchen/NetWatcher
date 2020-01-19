package com.tongchen.gank.di

import com.tongchen.basemodule.base.BaseApiHelper
import com.tongchen.basemodule.di.AbstractAppComponent
import com.tongchen.basemodule.di.AppScope
import com.tongchen.basemodule.di.BaseAppComponent
import dagger.Component

/**
 * @author TongChen
 * @date 2019/10/29  14:10
 * <p>
 * Desc:
 */
@AppScope
@Component(dependencies = [BaseAppComponent::class], modules = [GankAppModule::class])
interface GankAppComponent : AbstractAppComponent {

    //fun rxBus(): RxBus

    fun gankApiHelper(): BaseApiHelper
}
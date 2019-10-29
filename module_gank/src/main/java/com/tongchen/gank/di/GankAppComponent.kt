package com.tongchen.gank.di

import com.tongchen.basemodule.di.AbstractAppComponent
import com.tongchen.basemodule.di.BaseAppComponent
import com.tongchen.basemodule.di.AppScope
import dagger.Component

/**
 * @author TongChen
 * @date 2019/10/29  14:10
 * <p>
 * Desc:
 */
@AppScope
@Component(dependencies = [BaseAppComponent::class], modules = [GankModule::class])
interface GankAppComponent : AbstractAppComponent {

}
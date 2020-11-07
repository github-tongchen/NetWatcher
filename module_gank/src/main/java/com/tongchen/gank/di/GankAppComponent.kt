package com.tongchen.gank.di

import android.content.Context
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

    /** Note: 必须显式的提供 BaseAppComponent 所能提供的对象
     *
     * 理由：BaseAppComponent 中的对象对于 GankAppComponent 是可见的，
     * 但是对于依赖于 GankAppComponent 的上层 GankViewComponent 是不可见的，所以需要再次显式的声明
     */
    fun getContext(): Context

    fun gankApiHelper(): BaseApiHelper

}
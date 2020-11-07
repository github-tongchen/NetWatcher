package com.tongchen.gank.di

import android.content.Context
import androidx.fragment.app.Fragment
import com.tongchen.basemodule.di.BaseViewModule
import com.tongchen.basemodule.di.ViewScope
import com.tongchen.gank.biz.ui.fragment.CategoryFragment
import dagger.Component

/**
 * @author TongChen
 * @date 2020/01/09  15:45
 * <p>
 * Desc:
 */
@ViewScope
@Component(dependencies = [GankAppComponent::class], modules = [GankViewModule::class, BaseViewModule::class])
interface GankViewComponent {

    fun getContext(): Context

    fun getFragment(): Fragment


    //fun inject(activity Activity)
    fun inject(fragment: Fragment)
}
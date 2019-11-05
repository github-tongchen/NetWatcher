package com.tongchen.basemodule.di

import android.app.Activity
import dagger.Module
import dagger.Provides

/**
 * @author TongChen
 * @date 2019/10/29  17:57
 * <p>
 * Desc:
 * 使用原则：
 * 1. BaseViewComponent最好不要被其他module以dependencies方式进行关联
 * 2. 其他module中应该应用BaseViewModule 而不是 BaseViewComponent
 */
@Module
class BaseViewModule constructor(activity: Activity) {

    private var mActivity: Activity = activity

    @ViewScope
    @Provides
    fun provideActivity(): Activity {
        return mActivity
    }
}
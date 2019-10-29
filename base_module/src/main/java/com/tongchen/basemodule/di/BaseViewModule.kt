package com.tongchen.basemodule.di

import android.app.Activity
import dagger.Module
import dagger.Provides

/**
 * @author TongChen
 * @date 2019/10/29  17:57
 * <p>
 * Desc:
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
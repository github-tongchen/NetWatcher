package com.tongchen.basemodule.di

import android.app.Activity
import androidx.fragment.app.Fragment
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
class BaseViewModule {

    private lateinit var mActivity: Activity
    private lateinit var mFragment: Fragment

    constructor(activity: Activity) {
        mActivity = activity
    }

    constructor(fragment: Fragment) {
        mFragment = fragment
    }

    @ViewScope
    @Provides
    fun provideActivity(): Activity {
        return mActivity
    }

    @ViewScope
    @Provides
    fun provideFragment(): Fragment {
        return mFragment
    }
}
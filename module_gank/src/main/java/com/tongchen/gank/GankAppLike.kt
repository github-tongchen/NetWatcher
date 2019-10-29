package com.tongchen.gank

import android.util.Log
import com.tongchen.basemodule.BaseApplication
import com.tongchen.basemodule.di.AbstractAppComponent
import com.tongchen.basemodule.modulekit.AppComponentDelegate
import com.tongchen.componentservice.applicationlike.BaseApplicationLike
import com.tongchen.gank.di.DaggerGankAppComponent

/**
 * @author TongChen
 * @date 2019/10/23  14:32
 * <p>
 * Desc:
 */
class GankAppLike : BaseApplicationLike {

    private val componentDelegate = object : AppComponentDelegate {
        override fun initAppComponent(): AbstractAppComponent {
            return DaggerGankAppComponent.builder()
                .baseAppComponent(BaseApplication.mInstance.baseAppComponent())
                .build()
        }
    }

    private val TAG = "GankAppLike"

    override fun onCreate() {
        Log.d(TAG, "---onCreate")
    }

    override fun onTerminate() {
        Log.d(TAG, "---onTerminate")
    }

}
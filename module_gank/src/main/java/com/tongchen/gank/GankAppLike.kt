package com.tongchen.gank

import android.util.Log
import com.tongchen.basemodule.BaseApplication
import com.tongchen.basemodule.di.AbstractAppComponent
import com.tongchen.basemodule.di.kit.AppComponentDelegate
import com.tongchen.componentservice.applicationlike.BaseApplicationLike
import com.tongchen.gank.di.DaggerGankAppComponent
import com.tongchen.gank.di.GankDiKit

/**
 * @author TongChen
 * @date 2019/10/23  14:32
 * <p>
 * Desc:
 */
class GankAppLike : BaseApplicationLike {

    private val TAG = "GankAppLike"

    private val mComponentDelegate = object : AppComponentDelegate {
        override fun initAppComponent(): AbstractAppComponent {
            return DaggerGankAppComponent.builder()
                .baseAppComponent(BaseApplication.mInstance.baseAppComponent())
                .build()
        }
    }

    override fun onCreate() {
        Log.d(TAG, "---onCreate")

        GankDiKit.init(mComponentDelegate)
    }

    override fun onDestroy() {
        Log.d(TAG, "---onDestroy")
    }

}
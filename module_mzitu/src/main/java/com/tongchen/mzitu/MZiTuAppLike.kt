package com.tongchen.mzitu

import android.util.Log
import com.tongchen.basemodule.BaseApplication
import com.tongchen.basemodule.di.AbstractAppComponent
import com.tongchen.basemodule.di.kit.AppComponentDelegate
import com.tongchen.componentservice.applicationlike.BaseApplicationLike
import com.tongchen.mzitu.di.DaggerMZiTuAppComponent
import com.tongchen.mzitu.di.MZiTuDiKit

/**
 * @author TongChen
 * @date 2019/10/26  18:14
 * <p>
 * Desc:
 */
class MZiTuAppLike : BaseApplicationLike {

    private val TAG = "MZiTuAppLike"

    private val mComponentDelegate = object : AppComponentDelegate {
        override fun initAppComponent(): AbstractAppComponent {
            return DaggerMZiTuAppComponent.builder()
                .baseAppComponent(BaseApplication.mInstance.baseAppComponent())
                .build()
        }
    }

    override fun onCreate() {
        Log.d(TAG, "---onCreate")

        MZiTuDiKit.init(mComponentDelegate)
    }

    override fun onDestroy() {
        Log.d(TAG, "---onDestroy")
    }
}
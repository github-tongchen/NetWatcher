package com.tongchen.mzitu.arouter

import android.util.Log
import com.tongchen.componentservice.applicationlike.BaseApplicationLike

/**
 * @author TongChen
 * @date 2019/10/26  18:14
 * <p>
 * Desc:
 */
class MZiTuAppLike : BaseApplicationLike {

    private val TAG = "MZiTuAppLike"

    override fun onCreate() {
        Log.d(TAG, "---onCreate")
    }

    override fun onTerminate() {
        Log.d(TAG, "---onTerminate")
    }
}
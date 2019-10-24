package com.tongchen.gank

import android.util.Log
import com.tongchen.componentservice.applicationlike.BaseApplicationLike

/**
 * @author TongChen
 * @date 2019/10/23  14:32
 * <p>
 * Description:
 */
class GankAppLike : BaseApplicationLike {

    private val TAG = "GankAppLike"

    override fun onCreate() {
        Log.d(TAG, "---onCreate")
    }

    override fun onTerminate() {
        Log.d(TAG, "---onCreate")
    }

}
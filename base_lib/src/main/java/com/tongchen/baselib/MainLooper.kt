package com.tongchen.baselib

import android.os.Handler
import android.os.Looper

/**
 * @author TongChen
 * @date 2019/10/23  8:00
 * <p>
 * Desc:
 */
class MainLooper protected constructor(looper: Looper) : Handler(looper) {

    companion object {
        private val instance = MainLooper(Looper.getMainLooper())

        fun runOnUiThread(runnable: Runnable) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                runnable.run()
            } else {
                instance.post(runnable)
            }
        }
    }
}
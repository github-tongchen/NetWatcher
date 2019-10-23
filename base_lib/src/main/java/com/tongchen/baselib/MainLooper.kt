package com.tongchen.baselib

import android.os.Handler
import android.os.Looper

/**
 * @author TongChen
 * @date 2019/10/23  8:00
 * <p>
 * Desc:
 */
class MainLooper private constructor(looper: Looper) : Handler() {

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
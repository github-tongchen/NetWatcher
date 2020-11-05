package com.tongchen.baselib.util

/**
 * @author TongChen
 * @date 2020/11/05  18:26
 * <p>
 * Desc: 点击事件工具类
 */
class ClickUtils private constructor() {

    init {

        throw  IllegalStateException("ClickUtils doesn't need to be initialized!");
    }

    /**
     * 通过控制2次点击的时间间隔防止连续点击
     *
     * @return 是否在短时间内连续点击2次
     */
    companion object {
        private const val INTERVAL_TIME = 800
        private var mLastClickTime: Long = 0

        fun isFastDoubleClick(): Boolean {
            val currentTime = System.currentTimeMillis()
            val time = currentTime - mLastClickTime
            if (time in 1..INTERVAL_TIME) {
                return true
            }
            mLastClickTime = currentTime
            return false
        }

        fun isFastDoubleClick(interval: Int): Boolean {
            val currentTime = System.currentTimeMillis()
            val time = currentTime - mLastClickTime
            if (time in 1..interval) {
                return true
            }
            mLastClickTime = currentTime
            return false
        }
    }
}
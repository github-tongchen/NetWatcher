package com.tongchen.baselib.util

import android.util.Log


/**
 * @author TongChen
 * @date 2019/10/29  10:54
 * <p>
 * Desc: 日志工具类
 */
class LogUtils {


    companion object {

        /**
         * BuildConfig.DEBUG的值会随着编译版本变化，在Build Variants中选择"debug"时值为true,选择"release"值为false
         */
        private val isLog = true
        private const val DEBUG_LEVEL = 6

        private const val VERBOSE = 5
        private const val DEBUG = 4
        private const val INFO = 3
        private const val WARN = 2
        private const val ERROR = 1

        /**
         * 日志长度，超出长度则分段打印日志
         */
        private val MAX_LENGTH = 3500

        fun v(tag: String, msg: String) {
            if (DEBUG_LEVEL > VERBOSE && isLog) {
                Log.v(tag, msg)
            }
        }

        fun d(tag: String, msg: String) {
            if (DEBUG_LEVEL > DEBUG && isLog) {
                if (msg.length > MAX_LENGTH) {
                    debugLong(tag, msg)
                } else {
                    Log.d(tag, msg)
                }
            }
        }

        fun i(tag: String, msg: String) {
            if (DEBUG_LEVEL > INFO && isLog) {
                Log.i(tag, msg)
            }
        }

        fun w(tag: String, msg: String) {
            if (DEBUG_LEVEL > WARN && isLog) {
                Log.w(tag, msg)
            }
        }

        fun e(tag: String, msg: String) {
            if (DEBUG_LEVEL > ERROR && isLog) {
                Log.e(tag, msg)
            }
        }

        /**
         * 使用分节的方式来输出足够长度的message
         *
         * @param tag 日志标签
         * @param log 日志内容
         */
        private fun debugLong(tag: String, log: String) {
            var str = log.trim()
            str = str.trim { it <= ' ' }
            var index = 0
            val maxLength = MAX_LENGTH
            var subStr: String
            var i = 0
            while (index < str.length) {
                // java的字符不允许指定超过总的长度end
                subStr = if (str.length <= index + maxLength) {
                    str.substring(index)
                } else {
                    str.substring(index, index + maxLength)
                }
                Log.d(tag, "Log分段" + i + " " + subStr.trim { it <= ' ' })

                index += maxLength
                i++
            }
        }
    }

}

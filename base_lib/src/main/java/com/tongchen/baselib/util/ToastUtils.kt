package com.tongchen.baselib.util

import android.content.Context
import android.widget.Toast

/**
 * @author TongChen
 * @date 2020/11/05  18:29
 *
 *
 * Desc: Toast工具类
 */
class ToastUtils private constructor() {

    init {
        throw UnsupportedOperationException("ToastUtils doesn't need to be initialized!")
    }

    companion object {
        private var mToast: Toast? = null

        fun showShort(context: Context?, message: CharSequence?) {
            if (mToast == null) {
                mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            } else {
                mToast!!.setText(message)
                mToast!!.duration = Toast.LENGTH_SHORT
            }
            mToast!!.show()
        }

        fun showShort(context: Context?, message: Int) {
            if (mToast == null) {
                mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            } else {
                mToast!!.setText(message)
                mToast!!.duration = Toast.LENGTH_SHORT
            }
            mToast!!.show()
        }

        fun showLong(context: Context?, message: CharSequence?) {
            if (mToast == null) {
                mToast = Toast.makeText(context, message, Toast.LENGTH_LONG)
            } else {
                mToast!!.setText(message)
                mToast!!.duration = Toast.LENGTH_LONG
            }
            mToast!!.show()
        }

        fun showLong(context: Context?, message: Int) {
            if (mToast == null) {
                mToast = Toast.makeText(context, message, Toast.LENGTH_LONG)
            } else {
                mToast!!.setText(message)
                mToast!!.duration = Toast.LENGTH_LONG
            }
            mToast!!.show()
        }

        fun show(context: Context?, message: CharSequence?, duration: Int) {
            if (mToast == null) {
                mToast = Toast.makeText(context, message, duration)
            } else {
                mToast!!.setText(message)
                mToast!!.duration = duration
            }
            mToast!!.show()
        }

        fun show(context: Context?, message: Int, duration: Int) {
            if (mToast == null) {
                mToast = Toast.makeText(context, message, duration)
            } else {
                mToast!!.setText(message)
                mToast!!.duration = duration
            }
            mToast!!.show()
        }
    }

}
package com.tongchen.baselib.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * Created by TongChen at 19:11 on 2019/07/03.
 *
 *
 * Description: 屏幕尺寸工具类
 */
class ScreenUtils private constructor() {

    init {
        throw UnsupportedOperationException("ScreenUtils can't be initialized!")
    }

    companion object {

        /**
         * 通过系统资源文件获取状态栏高度
         *
         * @param context 上下文
         * @return 状态栏高度
         */
        fun getStatusBarHeightByResource(context: Context): Int {
            var statusBarHeight = 0
            //获取status_bar_height资源的ID
            val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                //根据资源ID获取响应的尺寸值
                statusBarHeight = context.resources.getDimensionPixelSize(resourceId)
            }
            return statusBarHeight
        }

        /**
         * 通过反射获取底部虚拟导航栏高度
         *
         * @param context 上下文
         * @return 底部虚拟导航栏高度
         */
        @SuppressLint("PrivateApi")
        fun getNavigationBarHeightByReflection(context: Context): Int {
            var navigationBarHeight = 0
            try {
                val clazz = Class.forName("com.android.internal.R\$dimen")
                val obj = clazz.newInstance()
                val field = clazz.getField("navigation_bar_height")
                val height = field[obj].toString().toInt()
                //  dp->px
                navigationBarHeight = context.resources.getDimensionPixelSize(height)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return navigationBarHeight
        }

        //  当前设备是否为平板
        private var mIsTablet: Boolean? = null

        /**
         * 获取屏幕宽高
         *
         * @param context 上下文
         * @return 屏幕宽高的数组
         */
        fun getScreenSize(context: Context): IntArray {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val width: Int
            var height: Int
            if (wm != null) {
                val dm = DisplayMetrics()
                //  屏幕的物理尺寸，获取的高度包含虚拟按键占用的高度(如果存在虚拟按键的话)
                wm.defaultDisplay.getRealMetrics(dm)
                width = dm.widthPixels
                height = dm.heightPixels
            } else {
                val dm = context.resources.displayMetrics
                width = dm.widthPixels
                //  会忽略虚拟按键高度
                height = dm.heightPixels
                //  如果有虚拟按键，加上虚拟按键高度
                if (hasNavigationBar(context)) {
                    height += getNavigationBarHeightByResource(context)
                }
            }
            return intArrayOf(width, height)
        }

        /**
         * 获取屏幕宽度
         *
         * @param context 上下文
         * @return 屏幕宽度
         */
        fun getScreenWidth(context: Context): Float {
            return getScreenSize(context)[0].toFloat()
        }

        /**
         * 获取屏幕高度
         *
         * @param context 上下文
         * @return 屏幕高度
         */
        fun getScreenHeight(context: Context): Float {
            return getScreenSize(context)[1].toFloat()
        }

        /**
         * 通过反射获取状态栏高度
         *
         * @param context 上下文
         * @return 状态栏高度
         */
        @SuppressLint("PrivateApi")
        fun getStatusBarHeightByReflection(context: Context): Int {
            var statusBarHeight = 0
            try {
                val clazz = Class.forName("com.android.internal.R\$dimen")
                val obj = clazz.newInstance()
                val field = clazz.getField("status_bar_height")
                val height = field[obj].toString().toInt()
                //  dp->px
                statusBarHeight = context.resources.getDimensionPixelSize(height)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return statusBarHeight
        }

        /**
         * 判断是否存在虚拟按键
         *
         * @param context 上下文
         * @return 是否存在虚拟按键， true 存在，false 不存在
         */
        fun hasNavigationBar(context: Context): Boolean {
            var hasNavigationBar = false
            val rs = context.resources
            val id = rs.getIdentifier("config_showNavigationBar", "bool", "android")
            if (id > 0) {
                hasNavigationBar = rs.getBoolean(id)
            }
            try {
                val systemPropertiesClass = Class.forName("android.os.SystemProperties")
                val m = systemPropertiesClass.getMethod("get", String::class.java)
                val navBarOverride = m.invoke(systemPropertiesClass, "qemu.hw.mainkeys") as String
                if ("1" == navBarOverride) {
                    hasNavigationBar = false
                } else if ("0" == navBarOverride) {
                    hasNavigationBar = true
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return hasNavigationBar
        }

        /**
         * 通过系统资源文件获取底部虚拟导航栏高度
         *
         * @param context 上下文
         * @return 底部虚拟导航栏高度
         */
        fun getNavigationBarHeightByResource(context: Context): Int {
            var navigationBarHeight = 0
            val resources = context.resources
            val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
            if (resourceId > 0) {
                navigationBarHeight = resources.getDimensionPixelSize(resourceId)
            }
            return navigationBarHeight
        }

        /**
         * 获取当前屏幕是横屏还是竖屏
         */
        fun getScreenOrientation(context: Context): Int {
            val cf = context.resources.configuration
            return cf.orientation
        }

        /**
         * 判断当前是否是横屏
         *
         * @param context 上下文
         * @return 当前是否是横屏
         */
        fun isLandscape(context: Context): Boolean {
            return context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        }

        /**
         * 判断当前是否是竖屏
         *
         * @param context 上下文
         * @return 当前是否是竖屏
         */
        fun isPortrait(context: Context): Boolean {
            return context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        }

        /**
         * 判断当前设备是否为平板
         *
         * @param context 上下文
         * @return 当前设备是否为平板
         */
        fun isTablet(context: Context): Boolean {
            if (mIsTablet == null) {
                mIsTablet = Configuration.SCREENLAYOUT_SIZE_MASK and context.resources.configuration.screenLayout >= Configuration.SCREENLAYOUT_SIZE_LARGE
            }
            return mIsTablet!!
        }
    }

}
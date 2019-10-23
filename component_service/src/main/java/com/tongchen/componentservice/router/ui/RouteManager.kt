package com.tongchen.componentservice.router.ui

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author TongChen
 * @date 2019/10/23  15:38
 * <p>
 * Description:
 */
class RouteManager {

    companion object {
        /**
         * 路由地址
         */
        val URL_MAIN_GANK = "/gank/main"
        val URL_MAIN_MZITU = "/mzitu/main"
        /**
         * 模块应用名
         */
        val MODULE_GANK = "gank"
        val MODULE_MZITU = "mzitu"

        fun initRouter(application: Application) {
            //  打印日志
            ARouter.openLog()
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug()
            ARouter.init(application)
        }

        fun home(context: Context) {
            val packageName = context.applicationContext.packageName
            val suffix = packageName.substringAfterLast(".")
            when (suffix) {
                MODULE_GANK -> navigation(URL_MAIN_GANK)
                MODULE_MZITU -> navigation(URL_MAIN_MZITU)
                else -> throw IllegalArgumentException("模块应用名$suffix 不存在，包名$packageName")
            }
        }

        fun navigation(path: String) {
            ARouter.getInstance().build(path).navigation()
        }
    }


}

sealed class Components {

}

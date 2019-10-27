package com.tongchen.netwatcher

import com.tongchen.basemodule.BaseApplication
import com.tongchen.componentservice.router.Router
import com.tongchen.componentservice.router.ui.RouteManager

/**
 * @author TongChen
 * @date 2019/09/07  15:34
 * <p>
 * Desc:
 */
class NWApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        Router.registerComponent("com.tongchen.gank.GankAppLike")
        Router.registerComponent("com.tongchen.mzitu.MZiTuAppLike")
    }


    //  此方法只在模拟器上生效
    override fun onTerminate() {
        super.onTerminate()

        Router.unregisterComponent("com.tongchen.gank.GankAppLike")
    }

    override fun initComponentDI() {

    }

    override fun registerRouter() {
        RouteManager.initRouter(mInstance)
    }
}
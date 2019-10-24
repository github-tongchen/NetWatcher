package com.tongchen.netwatcher

import android.app.Application
import com.tongchen.componentservice.router.Router

/**
 * @author TongChen
 * @date 2019/09/07  15:34
 * <p>
 * Desc:
 */
class NWApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Router.registerComponent("com.tongchen.gank.GankAppLike")
    }


    //  此方法只在模拟器上生效
    override fun onTerminate() {
        super.onTerminate()

        Router.unregisterComponent("com.tongchen.gank.GankAppLike")
    }


}
package com.tongchen.gank.com.tongchen.gank

import com.tongchen.basemodule.BaseApplication
import com.tongchen.basemodule.di.AbstractAppComponent
import com.tongchen.basemodule.modulekit.AppComponentDelegate
import com.tongchen.componentservice.router.ui.RouteManager
import com.tongchen.gank.di.DaggerGankAppComponent
import com.tongchen.gank.di.GankDiKit

/**
 * @author TongChen
 * @date 2019/10/31  16:54
 * <p>
 * Desc:
 */
class GankApplication : BaseApplication() {

    private val TAG = "GankApplication"

    private val mComponentDelegate = object : AppComponentDelegate {
        override fun initAppComponent(): AbstractAppComponent {
            return DaggerGankAppComponent.builder()
                .baseAppComponent(BaseApplication.mInstance.baseAppComponent())
                .build()
        }
    }

    override fun initComponentDi() {
        GankDiKit.init(mComponentDelegate)
    }

    override fun registerRouter() {
        RouteManager.initRouter(this)
    }
}
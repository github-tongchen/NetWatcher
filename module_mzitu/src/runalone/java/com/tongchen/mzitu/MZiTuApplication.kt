package com.tongchen.mzitu

import com.tongchen.basemodule.BaseApplication
import com.tongchen.basemodule.di.AbstractAppComponent
import com.tongchen.basemodule.di.kit.AppComponentDelegate
import com.tongchen.componentservice.router.ui.RouteManager
import com.tongchen.mzitu.di.DaggerMZiTuAppComponent
import com.tongchen.mzitu.di.MZiTuDiKit

/**
 * @author TongChen
 * @date 2020/01/17  18:24
 * <p>
 * Desc:
 */
class MZiTuApplication : BaseApplication() {

    private val TAG = "MZiTuApplication"

    private val mComponentDelegate = object : AppComponentDelegate {
        override fun initAppComponent(): AbstractAppComponent {
            return DaggerMZiTuAppComponent.builder()
                .baseAppComponent(BaseApplication.mInstance.baseAppComponent())
                .build()
        }
    }

    override fun initComponentDi() {
        MZiTuDiKit.init(mComponentDelegate)
    }

    override fun registerRouter() {
        RouteManager.initRouter(this)
    }
}
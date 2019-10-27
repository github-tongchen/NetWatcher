package com.tongchen.basemodule

import android.app.Application

/**
 * @author TongChen
 * @date 2019/10/26  15:03
 * <p>
 * Desc:
 */
abstract class BaseApplication : Application() {

    protected lateinit var mInstance: BaseApplication
    // BaseAppComponent 要保证唯一来自于BaseModuleKit， 即只能被创建一次
    private var mAppComponent: BaseAppComponent? = null

    override fun onCreate() {
        super.onCreate()
        mInstance = this

        initComponentDI()
        registerRouter()
    }

    fun baseAppComponent(): BaseAppComponent? {
        return if(mAppComponent==null) BaseModuleKit.getComponent() else null
    }

    abstract fun initComponentDI()

    abstract fun registerRouter()
}
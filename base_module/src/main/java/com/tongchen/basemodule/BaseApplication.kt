package com.tongchen.basemodule

import android.app.Application
import com.tongchen.basemodule.di.BaseAppComponent
import com.tongchen.basemodule.di.BaseAppModule
import com.tongchen.basemodule.di.DaggerBaseAppComponent
import com.tongchen.basemodule.modulekit.BaseModuleKit

/**
 * @author TongChen
 * @date 2019/10/26  15:03
 * <p>
 * Desc:
 */
abstract class BaseApplication : Application() {

    //  BaseAppComponent 要保证唯一来自于BaseModuleKit， 即只能被创建一次
    private var mAppComponent: BaseAppComponent? = null

    companion object {
        lateinit var mInstance: BaseApplication
            protected set
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this

        initComponentDI()
        registerRouter()
    }

    fun baseAppComponent(): BaseAppComponent? {
        return if (mAppComponent == null) {
            DaggerBaseAppComponent.builder().baseAppModule(
                BaseAppModule(mInstance)
            ).build()
        } else {
            mAppComponent
        }
    }

    abstract fun initComponentDI()

    abstract fun registerRouter()
}
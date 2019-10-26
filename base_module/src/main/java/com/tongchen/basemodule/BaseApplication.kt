package com.tongchen.basemodule

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

/**
 * @author TongChen
 * @date 2019/10/26  15:03
 * <p>
 * Desc:
 */
class BaseApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }
}
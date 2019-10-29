package com.tongchen.basemodule.modulekit

import com.tongchen.basemodule.di.AbstractAppComponent

/**
 * @author TongChen
 * @date 2019/10/29  18:16
 * <p>
 * Desc: 每个模块的 AbstractAppComponent 初始化接口
 */
interface AppComponentDelegate {

    fun initAppComponent(): AbstractAppComponent
}
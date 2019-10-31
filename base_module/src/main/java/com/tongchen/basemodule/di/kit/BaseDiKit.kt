package com.tongchen.basemodule.di.kit

import com.tongchen.basemodule.di.AbstractAppComponent

/**
 * @author TongChen
 * @date 2019/10/31  17:10
 * <p>
 * Desc: 模块的Dagger工具基类
 */
open class BaseDiKit {

    var mComponent: AbstractAppComponent? = null
        protected set

    fun init(appComponentDelegate: AppComponentDelegate) {
        if (mComponent == null) {
            mComponent = appComponentDelegate.initAppComponent()
        }
    }
}
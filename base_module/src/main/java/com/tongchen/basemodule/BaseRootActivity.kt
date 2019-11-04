package com.tongchen.basemodule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tongchen.basemodule.di.BaseViewComponent
import com.tongchen.basemodule.di.BaseViewModule
import com.tongchen.basemodule.di.DaggerBaseViewComponent

/**
 * @author TongChen
 * @date 2019/11/4  22:53
 * <p>
 * Desc:
 */
abstract class BaseRootActivity: AppCompatActivity() {

    private var mComponent: BaseViewComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        getComponent()
    }

    fun getComponent(): BaseViewComponent? {
        if (mComponent == null) {
            mComponent = DaggerBaseViewComponent.builder()
                .baseViewModule(BaseViewModule(this))
                .build()
        }
        return mComponent
    }

    abstract fun getLayoutId(): Int
}
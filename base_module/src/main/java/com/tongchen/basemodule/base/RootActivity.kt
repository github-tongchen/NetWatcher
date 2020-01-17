package com.tongchen.basemodule.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.tongchen.basemodule.di.BaseViewComponent
import com.tongchen.basemodule.di.BaseViewModule
import com.tongchen.basemodule.di.DaggerBaseViewComponent

/**
 * @author TongChen
 * @date 2020/01/06  14:47
 * <p>
 * Desc:
 */
abstract class RootActivity : AppCompatActivity() {

    val TAG = this.javaClass.simpleName

    private var mBaseViewComponent: BaseViewComponent? = null

    fun viewComponent(): BaseViewComponent? {
        if (mBaseViewComponent == null) {
            mBaseViewComponent = DaggerBaseViewComponent.builder()
                .baseViewModule(BaseViewModule(this))
                .build()
        }
        return mBaseViewComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewComponent()!!.inject(this)
    }

    @NonNull
    @LayoutRes
    abstract fun getLayoutId(): Int
}
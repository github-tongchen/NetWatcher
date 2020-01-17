package com.tongchen.basemodule.base

import android.app.Activity
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.tongchen.basemodule.di.BaseViewComponent
import com.tongchen.basemodule.di.BaseViewModule
import com.tongchen.basemodule.di.DaggerBaseViewComponent

/**
 * @author TongChen
 * @date 2020/01/06  14:48
 * <p>
 * Desc:
 */
abstract class RootFragment : Fragment() {

    val TAG = this.javaClass.simpleName

    private var mBaseViewComponent: BaseViewComponent? = null

    fun viewComponent(): BaseViewComponent? {
        if (mBaseViewComponent == null) {
            mBaseViewComponent = DaggerBaseViewComponent.builder()
                .baseViewModule(BaseViewModule(activity as Activity))
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
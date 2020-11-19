package com.tongchen.basemodule.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.tongchen.basemodule.FragmentBackHandler
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

    private val TAG = this.javaClass.simpleName

    private var mBaseViewComponent: BaseViewComponent? = null

    private lateinit var mFragmentBackHandler: FragmentBackHandler

    fun viewComponent(): BaseViewComponent? {
        if (mBaseViewComponent == null) {
            mBaseViewComponent = DaggerBaseViewComponent.builder()
                .baseViewModule(BaseViewModule(activity as Activity))
                .build()
        }
        return mBaseViewComponent
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity !is FragmentBackHandler) {
            throw ClassCastException("Hosting Activity must implement FragmentBackHandler")
        } else {
            this.mFragmentBackHandler = activity as FragmentBackHandler
        }
        //告诉Activity，当前Fragment在栈顶
        mFragmentBackHandler.showingFragment(this)
    }

    override fun onDetach() {
        super.onDetach()
        if (activity !is FragmentBackHandler) {
            throw ClassCastException("Hosting Activity must implement FragmentBackHandler")
        } else {
            this.mFragmentBackHandler = activity as FragmentBackHandler
        }
        //告诉Activity，当前Fragment已移除
        mFragmentBackHandler.showingFragment(null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewComponent()!!.inject(this)
    }

    /**
     * 继承RootFragment的子类需要自己处理返回键点击事件的时候重写此方法，默认不处理
     * Activity捕捉到物理返回键点击事件后会首先询问Fragment是否消费该事件
     * 如果没有Fragment消息时MainActivity自己才会消费该事件
     */
    open fun onBackPressed(): Boolean {
        return false
    }

    @NonNull
    @LayoutRes
    abstract fun getLayoutId(): Int
}
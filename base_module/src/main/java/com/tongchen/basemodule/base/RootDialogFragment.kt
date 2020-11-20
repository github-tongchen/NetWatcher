package com.tongchen.basemodule.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


/**
 * @author TongChen
 * @date 2020/11/20  15:54
 * <p>
 * Desc:
 */
abstract class RootDialogFragment : DialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val window: Window? = dialog!!.window
        //  一定要设置Background，如果不设置，window属性设置无效
        //  同时也解决设置 shape 圆角无效的问题
        //  一定要设置Background，如果不设置，window属性设置无效
        //  同时也解决设置 shape 圆角无效的问题
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun show(manager: FragmentManager, tag: String?) {
        //  防止重复添加 fragment 导致崩溃
        val transaction: FragmentTransaction = manager.beginTransaction()
        val fragment: Fragment? = manager.findFragmentByTag(tag)
        if (fragment != null) {
            transaction.remove(fragment)
        }
        transaction.commit()
        super.show(manager, tag)
    }

    @NonNull
    @LayoutRes
    abstract fun getLayoutId(): Int
}
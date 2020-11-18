package com.tongchen.basemodule.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.tongchen.basemodule.R

/**
 * @author TongChen
 * @date 2019/12/30  18:44
 * <p>
 * Desc:
 */
abstract class BaseFragment<DB : ViewDataBinding> : RootFragment() {

    protected lateinit var mDataBinding: DB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)

        return mDataBinding.root
    }

    override fun onDestroyView() {
        mDataBinding.unbind()
        super.onDestroyView()
    }

    fun startFragment(fragmentMgr: FragmentManager, containerId: Int, fragment: Fragment) {
        val transaction = fragmentMgr.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.fragment_translate_in,
            R.anim.fragment_translate_out,
            R.anim.fragment_translate_in,
            R.anim.fragment_translate_out
        )
        transaction.add(containerId, fragment).addToBackStack(null)
        transaction.commit()
    }
}
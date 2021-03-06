package com.tongchen.mzitu.arouter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.tongchen.componentservice.module.mzitu.MZiTuService
import com.tongchen.mzitu.R
import com.tongchen.mzitu.ui.fragment.MZiTuMainFragment

/**
 * @author TongChen
 * @date 2019/10/26  18:12
 * <p>
 * Desc:模块间服务的具体实现
 *
 * Note:path 必须以'/'开头，否则会找不到
 */
@Route(path = "/mzitu/service")
class MZiTuServiceImpl : MZiTuService {

    private lateinit var mFragment: Fragment

    private lateinit var mFragmentManager: FragmentManager
    private var mContainerId: Int = 0

    override fun getFragment(): Fragment {
        if (!this::mFragment.isInitialized) {
            mFragment = MZiTuMainFragment.newInstance()
        }
        return mFragment
    }

    override fun getTitle(): Int {
        return R.string.module_mzitu_app_name
    }

    override fun getToolbarColor(): Int {
        return R.color.module_mzitu_toolbar_bg
    }

    override fun setContainerId(containerId: Int) {
        mContainerId = containerId
    }

    override fun setFragmentMgr(fragmentManager: FragmentManager) {
        mFragmentManager = fragmentManager
    }

    override fun getContainerId(): Int {
        return mContainerId
    }

    override fun getFragmentMgr(): FragmentManager {
        return mFragmentManager
    }

    override fun init(context: Context?) {
    }
}
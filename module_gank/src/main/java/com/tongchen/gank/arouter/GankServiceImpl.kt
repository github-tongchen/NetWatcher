package com.tongchen.gank.arouter

import androidx.fragment.app.Fragment
import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.tongchen.componentservice.module.gank.GankService
import com.tongchen.gank.R
import com.tongchen.gank.biz.ui.fragment.GankMainFragment

/**
 * @author TongChen
 * @date 2019/10/21  21:48
 * <p>
 * Desc:模块间服务的具体实现
 *
 * Note:path 必须以'/'开头，否则会找不到
 */
@Route(path = "/gank/service")
class GankServiceImpl : GankService {

    private lateinit var mFragment: Fragment

    override fun getFragment(): Fragment {
        if (!this::mFragment.isInitialized) {
            mFragment = GankMainFragment.newInstance()
        }
        return mFragment
    }

    override fun getTitle(): Int {
        return R.string.module_gank_app_name
    }

    override fun getToolbarColor(): Int {
        return R.color.module_gank_toolbar_bg
    }

    override fun init(context: Context?) {
    }
}
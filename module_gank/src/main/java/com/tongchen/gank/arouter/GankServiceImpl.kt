package com.tongchen.gank.arouter

import androidx.fragment.app.Fragment
import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.tongchen.componentservice.module.gank.GankService
import com.tongchen.gank.R
import com.tongchen.gank.ui.fragment.GankMainFragment

/**
 * @author TongChen
 * @date 2019/10/21  21:48
 * <p>
 * Desc:模块间服务的具体实现
 */
@Route(path = "/gank/service")
class GankServiceImpl : GankService {

    override fun getFragment(): Fragment {
        return GankMainFragment.newInstance()
    }

    override fun getNameRes(): Int {
        return R.string.module_gank_app_name
    }

    override fun init(context: Context?) {
    }
}
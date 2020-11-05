package com.tongchen.mzitu.arouter

import android.content.Context
import androidx.fragment.app.Fragment
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

    override fun getFragment(): Fragment {
        return MZiTuMainFragment.newInstance()
    }

    override fun getTitle(): Int {
        return R.string.module_mzitu_app_name
    }

    override fun getToolbarColor(): Int {
        return R.color.module_mzitu_toolbar_bg
    }

    override fun init(context: Context?) {
    }
}
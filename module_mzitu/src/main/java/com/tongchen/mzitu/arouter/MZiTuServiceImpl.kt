package com.tongchen.mzitu.arouter

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.tongchen.componentservice.module.mzitu.MZiTuService

/**
 * @author TongChen
 * @date 2019/10/26  18:12
 * <p>
 * Desc:模块间服务的具体实现
 */
@Route(path = "mzitu/service")
class MZiTuServiceImpl : MZiTuService {

    override fun getFragment(): Fragment {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun init(context: Context?) {
    }
}
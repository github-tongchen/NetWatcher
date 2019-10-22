package com.tongchen.gank.arouter

import androidx.fragment.app.Fragment
import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.tongchen.componentservice.module.gank.GankService

/**
 * @author TongChen
 * @date 2019/10/21  21:48
 * <p>
 * Desc:模块间服务的具体实现
 */
@Route(path="gank/service")
class GankServiceImpl : GankService {

    override fun getFragment(): Fragment {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun init(context: Context?) {
    }
}
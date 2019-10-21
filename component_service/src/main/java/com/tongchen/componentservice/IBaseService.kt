package com.tongchen.componentservice

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * @author TongChen
 * @date 2019/10/21  21:38
 * <p>
 * Desc:
 */
interface IBaseService : IProvider {

    fun getFragment(): Fragment
}
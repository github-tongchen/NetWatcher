package com.tongchen.componentservice.module

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * @author TongChen
 * @date 2019/10/21  21:38
 * <p>
 * Desc:业务模块间数据共享接口，比如共享Bean
 */
interface BaseService : IProvider {

    fun getFragment(): Fragment

    fun getTitle(): Int

    fun getToolbarColor(): Int
}
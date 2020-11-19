package com.tongchen.basemodule

import com.tongchen.basemodule.base.RootFragment

/**
 * @author TongChen
 * @date 2019/11/22  10:40
 * <p>
 * Desc: 使Fragment能够处理返回事件
 */
interface FragmentBackHandler {

    fun showingFragment(showingFragment: RootFragment?)
}
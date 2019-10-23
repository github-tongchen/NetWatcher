package com.tongchen.componentservice.applicationlike

/**
 * @author TongChen
 * @date 2019/10/22  17:38
 *
 * Desc:业务模块类Application接口，用于在作为模块执行时，执行需要在Application中初始化的操作
 */
interface BaseApplicationLike {

    fun onCreate()

    fun onTerminate()
}
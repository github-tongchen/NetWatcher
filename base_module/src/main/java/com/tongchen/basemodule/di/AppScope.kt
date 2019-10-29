package com.tongchen.basemodule.di

import javax.inject.Scope

/**
 * @author TongChen
 * @date 2019/10/29  11:08
 * <p>
 * Desc: app 级别的注入，用于标识模块中的 AbstractAppComponent
 */
@Retention(AnnotationRetention.RUNTIME)
@Scope
annotation class AppScope

package com.tongchen.componentservice.router

import android.text.TextUtils
import androidx.annotation.NonNull
import com.tongchen.componentservice.applicationlike.BaseApplicationLike
import kotlin.collections.HashMap

/**
 * @author TongChen
 * @date 2019/10/23  10:40
 * <p>
 * Desc:路由中心，用于控制组件动态的加载/移除和 Service的 add/remove/get
 */
object Router {

    //  模块的Service集合
    private val serviceMap = HashMap<String, Any>()
    //  注册的模块的集合
    private val componentMap = HashMap<String, BaseApplicationLike>()

    @Synchronized
    fun <T : Any> addService(@NonNull clazz: Class<T>, @NonNull t: T) {
        serviceMap[clazz.simpleName] = t
    }

    @Synchronized
    fun <T : Any> getService(@NonNull clazz: Class<T>): T? {
        val hasService = serviceMap.containsKey(clazz.simpleName)
        return if (!hasService) null else serviceMap[clazz.simpleName] as T
    }

    @Synchronized
    fun <T : Any> removeService(@NonNull clazz: Class<T>) {
        if (serviceMap.containsKey(clazz.simpleName)) {
            serviceMap.remove(clazz.simpleName)
        }
    }

    /**
     * 注册组件
     *
     * @param className 组件名
     */
    @Synchronized
    fun registerComponent(@NonNull className: String) {
        if (TextUtils.isEmpty(className)) {
            return
        }
        if (componentMap.containsKey(className)) {
            return
        }
        try {
            val clazz = Class.forName(className)
            val applicationLike = clazz.newInstance() as BaseApplicationLike
            applicationLike.onCreate()
            componentMap[className] = applicationLike
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 反注册组件
     *
     * @param className 组件名
     */
    @Synchronized
    fun unregisterComponent(@NonNull className: String) {
        if (TextUtils.isEmpty(className)) {
            return
        }
        try {
            if (componentMap.containsKey(className)) {
                componentMap[className]!!.onDestroy()
                componentMap.remove(className)
                return
            }
            val clazz = Class.forName(className)
            val applicationLike = clazz.newInstance() as BaseApplicationLike
            applicationLike.onDestroy()
            componentMap.remove(className)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
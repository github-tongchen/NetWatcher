package com.tongchen.basemodule.base

import androidx.annotation.NonNull
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.tongchen.baselib.util.LifecycleUtils
import com.uber.autodispose.AutoDisposeConverter

/**
 * @author TongChen
 * @date 2019/11/06  15:02
 * <p>
 * Desc:
 */
interface BaseMvpContract {

    abstract class MvpModel : LifecycleObserver {

        protected var mApiHelper: BaseApiHelper? = null

        constructor(apiHelper: BaseApiHelper) {
            mApiHelper = apiHelper
        }

        fun onDestroy() {
            mApiHelper = null
        }
    }


    interface MvpView


    abstract class MvpPresenter<M : MvpModel, V : MvpView> : LifecycleObserver {

        protected var mModel: M? = null
        protected var mView: V? = null
        private var mLifecycleOwner: LifecycleOwner? = null

        constructor(@NonNull model: M, @NonNull view: V) {
            mModel = model
            mView = view
        }

        fun setLifecycleOwner(@NonNull owner: LifecycleOwner) {
            mLifecycleOwner = owner
        }

        protected fun <T> bindLifecycle(): AutoDisposeConverter<T> {
            val owner = mLifecycleOwner ?: throw NullPointerException("mLifecycleOwner is null")
            return LifecycleUtils.bindLifecycle(owner)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        protected fun onCreate(@NonNull owner: LifecycleOwner) {
            mLifecycleOwner = owner
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        protected fun onStart(@NonNull owner: LifecycleOwner) {

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        protected fun onResume(@NonNull owner: LifecycleOwner) {

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        protected fun onPause(@NonNull owner: LifecycleOwner) {

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        protected fun onStop(@NonNull owner: LifecycleOwner) {

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        protected fun onDestroy(@NonNull owner: LifecycleOwner) {
            mModel?.onDestroy()
            mModel = null
            mView = null
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        protected fun onLifecycleChanged(@NonNull owner: LifecycleOwner, @NonNull event: Lifecycle.Event) {

        }
    }
}
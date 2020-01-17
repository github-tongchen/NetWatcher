package com.tongchen.basemodule.base

import androidx.annotation.MainThread
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

        private var mBaseApiHelper: BaseApiHelper? = null

        constructor(apiHelper: BaseApiHelper) {
            mBaseApiHelper = apiHelper
        }

        fun onDestroy() {
            mBaseApiHelper = null

        }
    }


    interface MvpView


    abstract class MvpPresenter<M : MvpModel, V : MvpView> : LifecycleObserver {

        private var mModel: M? = null
        private var mLifecycleOwner: LifecycleOwner? = null

        constructor(model: M) {
            mModel = model
        }

        fun setLifecycleOwner(owner: LifecycleOwner) {
            mLifecycleOwner = owner
        }

        protected fun <T> bindLifecycle(): AutoDisposeConverter<T> {
            val owner = mLifecycleOwner ?: throw NullPointerException("mLifecycleOwner is null")
            return LifecycleUtils.bindLifecycle(owner)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        protected fun onCreate(@NonNull owner: LifecycleOwner) {

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        protected fun onStart(@NonNull owner: LifecycleOwner) {

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        @MainThread
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
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        protected fun onLifecycleChanged(@NonNull owner: LifecycleOwner, @NonNull event: Lifecycle.Event) {

        }
    }
}
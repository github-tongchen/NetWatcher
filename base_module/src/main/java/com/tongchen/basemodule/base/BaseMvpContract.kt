package com.tongchen.basemodule.base

import androidx.annotation.NonNull
import androidx.annotation.UiThread
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.tongchen.baselib.util.LifecycleUtils
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit

/**
 * @author TongChen
 * @date 2019/11/06  15:02
 * <p>
 * Desc:
 */
interface BaseMvpContract {

    abstract class MvpModel : LifecycleObserver {

        private var mBaseApi: BaseApi? = null

        constructor(baseApi: BaseApi) {
            mBaseApi = baseApi
        }

        fun onDestroy() {
            mBaseApi = null

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy(owner: LifecycleOwner) {

            owner.lifecycle.removeObserver(this)
        }
    }


    interface MvpView


    abstract class MvpPresenter<M : MvpModel, V : MvpView> : LifecycleObserver {

        private var mModel: M? = null
        private var mViewRef: WeakReference<V>? = null

        constructor(model: M) {
            mModel = model
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreate(owner: LifecycleOwner) {

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onStart(owner: LifecycleOwner) {

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume(owner: LifecycleOwner) {

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onPause(owner: LifecycleOwner) {

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStop(owner: LifecycleOwner) {

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy(owner: LifecycleOwner) {

        }

    }
}
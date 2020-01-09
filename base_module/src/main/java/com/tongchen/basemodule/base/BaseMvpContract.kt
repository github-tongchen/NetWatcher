package com.tongchen.basemodule.base

import androidx.annotation.NonNull
import androidx.annotation.UiThread
import java.lang.ref.WeakReference

/**
 * @author TongChen
 * @date 2019/11/06  15:02
 * <p>
 * Desc:
 */
interface BaseMvpContract {

    interface MvpModel {

        fun onDestroy()
    }


    interface MvpView
    

    abstract class MvpPresenter<M : MvpModel, V : MvpView> {

        private var mModel: M? = null
        private var mViewRef: WeakReference<V>? = null

        constructor(model: M) {
            mModel = model
        }

        @UiThread
        fun attachView(@NonNull view: V?) {
            if (view != null) {
                mViewRef = WeakReference(view)
            } else {
                throw NullPointerException("View can not be null when use method attachView() in MVPPresenter")
            }
        }

        @UiThread
        fun detachView() {
            if (mViewRef != null) {
                mViewRef!!.clear()
                mViewRef = null
            }

            if (mModel != null) {
                mModel!!.onDestroy()
                mModel = null
            }
        }

        @UiThread
        fun getView(): V? {
            if (isViewAttached()) {
                return mViewRef!!.get()
            }
            return null
        }

        @UiThread
        fun isViewAttached(): Boolean {
            if (mViewRef != null && mViewRef!!.get() != null) {
                return true
            } else {
                throw IllegalStateException("View " + " not attached to Presenter " + this.javaClass.simpleName)
            }
        }

    }
}
package com.tongchen.basemodule.base

import androidx.annotation.NonNull
import androidx.annotation.UiThread

/**
 * @author TongChen
 * @date 2019/11/06  15:02
 * <p>
 * Desc:
 */
interface BaseMvpContract {

    interface MvpModel

    interface MvpView

    abstract class MvpPresenter<V : MvpView> {

        @UiThread
        fun attachView(@NonNull view: V) {

        }

        @UiThread
        fun detachView() {

        }

        @UiThread
        fun destroy() {

        }
    }
}
package com.tongchen.basemodule

import android.os.Bundle
import androidx.annotation.NonNull
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.hannesdorfmann.mosby3.mvp.delegate.ActivityMvpDelegate
import com.hannesdorfmann.mosby3.mvp.delegate.MvpDelegateCallback
import com.hannesdorfmann.mosby3.mvp.delegate.ActivityMvpDelegateImpl


/**
 * @author TongChen
 * @date 2019/09/07  14:05
 * <p>
 * Desc:
 */
abstract class BaseActivity<V : MvpView?, P : MvpPresenter<V>?> : BaseRootActivity(),
    MvpView, MvpDelegateCallback<V, P> {

    //  星号投影
    protected var mvpDelegate: ActivityMvpDelegate<*, *>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

//        mvpDelegate.onCreate(savedInstanceState)
    }


    @NonNull
    protected fun getMVPDelegate(): ActivityMvpDelegate<*, *>? {
        if (mvpDelegate == null) {
            mvpDelegate = ActivityMvpDelegateImpl<V, P>(this, this, true)
        }

        return mvpDelegate
    }

}
package com.tongchen.basemodule

import android.os.Bundle
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.hannesdorfmann.mosby3.mvp.delegate.ActivityMvpDelegate
import com.hannesdorfmann.mosby3.mvp.delegate.ActivityMvpDelegateImpl
import com.hannesdorfmann.mosby3.mvp.delegate.MvpDelegateCallback


/**
 * @author TongChen
 * @date 2019/09/07  14:05
 * <p>
 * Desc:
 */
abstract class BaseActivity<V : MvpView?, P : MvpPresenter<V>?> : AppCompatActivity(),
    MvpDelegateCallback<V, P> {

    lateinit var mvpDelegate: ActivityMvpDelegate<V, P>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

//        mvpDelegate.onCreate(savedInstanceState)
    }

    abstract fun getLayoutId(): Int

    @NonNull
    protected fun getMvpDelegate1(): ActivityMvpDelegate<V, P> {
        if (mvpDelegate == null) {
            mvpDelegate = ActivityMvpDelegateImpl(this, this, true)
        }

        return mvpDelegate
    }

}
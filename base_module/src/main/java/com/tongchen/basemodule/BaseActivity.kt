package com.tongchen.basemodule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.hannesdorfmann.mosby3.mvp.delegate.MvpDelegateCallback


/**
 * @author TongChen
 * @date 2019/09/07  14:05
 * <p>
 * Desc:
 */
abstract class BaseActivity<V : MvpView, P : MvpPresenter<V>> :
    AppCompatActivity(), MvpDelegateCallback<V, P> {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        injectActivity()
    }

    abstract fun getLayoutId(): Int

    abstract fun injectActivity()
}
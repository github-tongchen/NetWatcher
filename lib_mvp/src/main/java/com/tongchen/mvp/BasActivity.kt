package com.tongchen.mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.hannesdorfmann.mosby3.mvp.delegate.MvpDelegateCallback


/**
 * Created by TongChen at 14:05 on 2019/09/07.
 *
 * Description:
 */
abstract class BaseActivity<V : MvpView, P : MvpPresenter<V>> :
    AppCompatActivity(), MvpDelegateCallback<V, P> {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        loadContent()
        injectActivity()

    }

    abstract fun getLayoutId(): Int

    abstract fun loadContent()

    abstract fun injectActivity()
}
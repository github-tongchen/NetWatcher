package com.tongchen.basemodule.mvp

import android.os.Bundle
import androidx.annotation.NonNull
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.hannesdorfmann.mosby3.mvp.delegate.ActivityMvpDelegate
import com.hannesdorfmann.mosby3.mvp.delegate.MvpDelegateCallback
import com.hannesdorfmann.mosby3.mvp.delegate.ActivityMvpDelegateImpl
import com.tongchen.basemodule.BaseActivity
import javax.inject.Inject


/**
 * @author TongChen
 * @date 2019/09/07  14:05
 * <p>
 * Desc: mosby MVP的修改版
 */
abstract class BaseMvpActivity<V : MvpView, P : MvpPresenter<V>> : BaseActivity(), MvpView, MvpDelegateCallback<V, P> {

    //  星号投影
    protected var mMvpDelegate: ActivityMvpDelegate<*, *>? = null
    @Inject
    lateinit var mPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMVPDelegate()?.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        getMVPDelegate()?.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        getMVPDelegate()?.onSaveInstanceState(outState)
    }

    override fun onPause() {
        super.onPause()
        getMVPDelegate()?.onPause()
    }

    override fun onResume() {
        super.onResume()
        getMVPDelegate()?.onResume()
    }

    override fun onStart() {
        super.onStart()
        getMVPDelegate()?.onStart()
    }

    override fun onStop() {
        super.onStop()
        getMVPDelegate()?.onStop()
    }

    override fun onRestart() {
        super.onRestart()
        getMVPDelegate()?.onRestart()
    }

    override fun onContentChanged() {
        super.onContentChanged()
        getMVPDelegate()?.onContentChanged()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        getMVPDelegate()?.onPostCreate(savedInstanceState)
    }

    @NonNull
    override fun createPresenter(): P {
        return mPresenter
    }

    @NonNull
    override fun getPresenter(): P {
        return mPresenter
    }

    override fun setPresenter(@NonNull presenter: P) {
        mPresenter = presenter
    }

    @NonNull
    override fun getMvpView(): V {
        return this as V
    }

    @NonNull
    protected fun getMVPDelegate(): ActivityMvpDelegate<*, *>? {
        if (mMvpDelegate == null) {
            mMvpDelegate = ActivityMvpDelegateImpl<V, P>(this, this, true)
        }
        return mMvpDelegate
    }

}
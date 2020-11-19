package com.tongchen.basemodule.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import javax.inject.Inject

/**
 * @author TongChen
 * @date 2019/09/07  14:05
 * <p>
 * Desc:
 */
abstract class BaseMvpActivity<DB : ViewDataBinding, M : BaseMvpContract.MvpModel, V : BaseMvpContract.MvpView, P : BaseMvpContract.MvpPresenter<M, V>> :
    BaseActivity<DB>(), BaseMvpContract.MvpView {

    @Inject
    lateinit var mPresenter: P


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject2Activity()

        //mPresenter.setLifecycleOwner(this)
        lifecycle.addObserver(mPresenter)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(mPresenter)
    }

    abstract fun inject2Activity()
}
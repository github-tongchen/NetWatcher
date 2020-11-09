package com.tongchen.basemodule.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import javax.inject.Inject

/**
 * @author TongChen
 * @date 2019/11/06  11:59
 * <p>
 * Desc:
 */
abstract class BaseMvpFragment<DB : ViewDataBinding, M : BaseMvpContract.MvpModel, V : BaseMvpContract.MvpView, P : BaseMvpContract.MvpPresenter<M, V>> :
    BaseFragment<DB>(), BaseMvpContract.MvpView {

    @Inject
    lateinit var mPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //mPresenter.setLifecycleOwner(this)
//        lifecycle.addObserver(mPresenter)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(mPresenter)
    }

}
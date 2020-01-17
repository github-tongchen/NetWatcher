package com.tongchen.gank.base

import android.app.Activity
import androidx.databinding.ViewDataBinding
import com.tongchen.basemodule.base.BaseMvpContract
import com.tongchen.basemodule.base.BaseMvpFragment
import com.tongchen.basemodule.di.BaseViewModule
import com.tongchen.gank.di.*

/**
 * @author TongChen
 * @date 2020/01/17  11:53
 * <p>
 * Desc:
 */
abstract class GankBaseMvpFragment<DB : ViewDataBinding, M : BaseMvpContract.MvpModel, V : BaseMvpContract.MvpView, P : BaseMvpContract.MvpPresenter<M, V>>
    : BaseMvpFragment<DB, M, V, P>() {

    private var mViewComponent: GankViewComponent? = null

    fun gankViewComponent(): GankViewComponent? {
        if (mViewComponent == null) {
            mViewComponent = DaggerGankViewComponent.builder()
                .gankAppComponent(GankDiKit.mComponent as GankAppComponent)
                .baseViewModule(BaseViewModule(activity as Activity))
                .build()
        }
        return mViewComponent
    }
}
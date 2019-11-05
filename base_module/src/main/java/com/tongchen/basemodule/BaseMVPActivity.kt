package com.tongchen.basemodule

import android.os.Bundle
import android.view.View
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.MvpLceViewStateActivity
import com.tongchen.basemodule.di.BaseViewComponent

/**
 * @author TongChen
 * @date 2019/09/07  14:05
 * <p>
 * Desc:
 */
abstract class BaseMVPActivity<CV : View, M, V : MvpLceView<M>, P : MvpBasePresenter<V>> :
    MvpLceViewStateActivity<CV, M, V, P>() {

    private var mComponent: BaseViewComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

    }


    abstract fun getLayoutId(): Int

}
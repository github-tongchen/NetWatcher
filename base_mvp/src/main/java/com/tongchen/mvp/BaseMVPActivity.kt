package com.tongchen.mvp

import android.os.Bundle
import android.view.View
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.MvpLceViewStateActivity

/**
 * Created by TongChen at 14:05 on 2019/09/07.
 *
 * Description:
 */
abstract class BaseMVPActivity<CV : View, M, V : MvpLceView<M>, P : MvpBasePresenter<V>> :
    MvpLceViewStateActivity<CV, M, V, P>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        injectActivity()
    }

    abstract fun getLayoutId(): Int

    abstract fun injectActivity()
}
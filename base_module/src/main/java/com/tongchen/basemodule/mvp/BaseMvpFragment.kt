package com.tongchen.basemodule.mvp

import android.view.View
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.MvpLceViewStateFragment

/**
 * @author TongChen
 * @date 2019/11/4  23:02
 * <p>
 * Desc:
 */
abstract class BaseMvpFragment<CV : View, M, V : MvpLceView<M>, P : MvpPresenter<V>> :
    MvpLceViewStateFragment<CV, M, V, P>() {
}
package com.tongchen.basemodule

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * @author TongChen
 * @date 2019/11/06  11:59
 * <p>
 * Desc:
 */
abstract class BaseMvpFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ///todo: presenter attachView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ///todo: presenter detachView
    }
}
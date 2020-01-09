package com.tongchen.gank.ui

import android.os.Bundle
import android.view.View
import com.tongchen.basemodule.base.BaseFragment
import com.tongchen.basemodule.di.BaseViewModule
import com.tongchen.gank.R
import com.tongchen.gank.databinding.ModuleGankFragmentTextBinding
import com.tongchen.gank.di.*
import com.tongchen.gank.entity.GankResult

/**
 * @author TongChen
 * @date 2020/01/09  15:09
 * <p>
 * Desc:
 */
class GankTextFragment : BaseFragment<ModuleGankFragmentTextBinding>() {

    private var mDataBindingComponent: GankDataBindingComponent? = null

    companion object {
        internal const val ARG_GANK_RESULT = "gank_result"

        fun newInstance(result: GankResult): GankTextFragment {
            val fragment = GankTextFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_GANK_RESULT, result)
            fragment.arguments = bundle
            return fragment
        }
    }


    fun dataBindingComponent(): GankDataBindingComponent? {
        if (mDataBindingComponent == null) {
            mDataBindingComponent = DaggerGankDataBindingComponent.builder()
                .gankAppComponent(GankDiKit.mComponent as GankAppComponent)
                .baseViewModule(BaseViewModule(this.activity!!))
                .build()
        }
        return mDataBindingComponent
    }

    override fun getLayoutId(): Int = R.layout.module_gank_fragment_text

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBindingComponent()?.inject(this)
    }
}
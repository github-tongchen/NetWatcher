package com.tongchen.gank.biz.ui.fragment

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.tongchen.gank.R
import com.tongchen.gank.base.GankBaseDBFragment
import com.tongchen.gank.databinding.ModuleGankFragmentTextBinding
import com.tongchen.gank.biz.entity.GankResult

/**
 * @author TongChen
 * @date 2020/01/09  15:09
 * <p>
 * Desc:
 */

@Route(path = "/gank/fragment")
class ContentTextFragment : GankBaseDBFragment<ModuleGankFragmentTextBinding>() {

    companion object {
        internal const val ARG_GANK_RESULT = "gank_result"

        fun newInstance(result: GankResult): ContentTextFragment {
            val fragment = ContentTextFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_GANK_RESULT, result)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun inject2Fragment() {
        dataBindingComponent()?.inject(this)
    }

    override fun getLayoutId(): Int = R.layout.module_gank_fragment_text

}
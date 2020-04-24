package com.tongchen.gank.ui.fragment

import com.tongchen.basemodule.base.BaseFragment
import com.tongchen.gank.R
import com.tongchen.gank.databinding.ModuleGankFragmentMainBinding


class GankMainFragment : BaseFragment<ModuleGankFragmentMainBinding>() {

    companion object {
        fun newInstance(): GankMainFragment = GankMainFragment()
    }

    override fun getLayoutId(): Int = R.layout.module_gank_fragment_main


}

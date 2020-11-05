package com.tongchen.mzitu.ui.fragment

import com.tongchen.basemodule.base.BaseFragment
import com.tongchen.mzitu.R
import com.tongchen.mzitu.databinding.ModuleMzituFragmentMainBinding

class MZiTuMainFragment : BaseFragment<ModuleMzituFragmentMainBinding>() {

    companion object {
        fun newInstance(): MZiTuMainFragment = MZiTuMainFragment()
    }

    override fun getLayoutId(): Int = R.layout.module_mzitu_fragment_main
}
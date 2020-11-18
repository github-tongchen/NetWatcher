package com.tongchen.gank.biz.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.tongchen.gank.R
import com.tongchen.gank.base.GankBaseDBFragment
import com.tongchen.gank.biz.entity.Category
import com.tongchen.gank.biz.ui.adapter.FragmentAdapter
import com.tongchen.gank.databinding.ModuleGankFragmentMainBinding
import kotlinx.android.synthetic.main.module_gank_fragment_main.*


class GankMainFragment : GankBaseDBFragment<ModuleGankFragmentMainBinding>() {

    private lateinit var mCategoryList: MutableList<Category>
    private lateinit var mFragmentList: MutableList<Fragment>

    companion object {
        fun newInstance(): GankMainFragment = GankMainFragment()
    }

    override fun getLayoutId(): Int = R.layout.module_gank_fragment_main

    override fun inject2Fragment() {
        dataBindingComponent()?.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDatas()

        fab.setOnClickListener {
            val index = viewpager.currentItem
            (mFragmentList[index] as CategoryFragment).back2Top()
        }
    }

    private fun initDatas() {
        initCategories()
        initFragments()

        val adapter = FragmentAdapter(childFragmentManager, mFragmentList, mCategoryList)
        viewpager.adapter = adapter
        tabLyt.setupWithViewPager(viewpager)
    }

    private fun initCategories() {
        mCategoryList = mutableListOf(
            Category.Builder().title("全部").type("All").index(0).build(),
            Category.Builder().title("Android").type("Android").index(1).build(),
            Category.Builder().title("iOS").type("iOS").index(2).build(),
            Category.Builder().title("Flutter").type("Flutter").index(3).build(),
            Category.Builder().title("前端").type("frontend").index(4).build(),
            Category.Builder().title("后端").type("backend").index(4).build(),
            Category.Builder().title("APP").type("app").index(5).build(),
            Category.Builder().title("妹纸").type("Girl").index(6).build()
        )
    }

    private fun initFragments() {
        mFragmentList = mutableListOf()
        for (i in 0 until mCategoryList.size) {
            val fragment = CategoryFragment.newInstance(mCategoryList[i])
            mFragmentList.add(fragment)
        }
    }


}

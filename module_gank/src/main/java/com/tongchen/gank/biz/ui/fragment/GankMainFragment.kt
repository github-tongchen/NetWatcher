package com.tongchen.gank.biz.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.tongchen.baselib.util.LogUtils
import com.tongchen.basemodule.base.BaseFragment
import com.tongchen.gank.R
import com.tongchen.gank.base.GankBaseDBFragment
import com.tongchen.gank.biz.entity.Category
import com.tongchen.gank.databinding.ModuleGankFragmentMainBinding
import kotlinx.android.synthetic.main.module_gank_fragment_main.*
import javax.inject.Inject


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

        val fragment = CategoryFragment.newInstance(mCategoryList[0])
        val transaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.test, fragment)
        transaction.commit()


        /*val adapter = FragmentAdapter(childFragmentManager, mFragmentList, mCategoryList)
        viewpager.adapter = adapter
        tabLyt.setupWithViewPager(viewpager)*/
    }

    private fun initCategories() {
        mCategoryList = mutableListOf(
            Category.Builder().categoryName("全部").requestName("all").index(0).build(),
            Category.Builder().categoryName("Android").requestName("Android").index(1).build(),
            Category.Builder().categoryName("iOS").requestName("iOS").index(2).build(),
            Category.Builder().categoryName("前端").requestName("前端").index(3).build(),
            Category.Builder().categoryName("拓展资源").requestName("拓展资源").index(4).build(),
            Category.Builder().categoryName("休息视频").requestName("休息视频").index(5).build(),
            Category.Builder().categoryName("瞎推荐").requestName("瞎推荐").index(6).build(),
            Category.Builder().categoryName("App").requestName("App").index(7).build(),
            Category.Builder().categoryName("福利").requestName("福利").index(8).build()
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

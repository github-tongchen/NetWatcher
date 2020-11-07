package com.tongchen.gank.biz.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.tongchen.gank.biz.entity.Category

/**
 * @author TongChen
 * @date 2019/11/20  15:19
 * <p>
 * Desc:
 */
class FragmentAdapter : FragmentPagerAdapter {

    private lateinit var mFragmentList: MutableList<Fragment>
    private lateinit var mCategoryList: MutableList<Category>

    constructor(fragmentManager: FragmentManager) : super(fragmentManager) {

    }

    constructor(fragmentManager: FragmentManager, fragments: MutableList<Fragment>, categories: MutableList<Category>) : super(fragmentManager) {
        mFragmentList = fragments
        mCategoryList = categories
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mCategoryList[position].mCategoryName
    }
}
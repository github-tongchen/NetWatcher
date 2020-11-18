package com.tongchen.gank.biz.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.tongchen.baselib.util.LogUtils
import com.tongchen.baselib.util.ToastUtils
import com.tongchen.basewidget.SimpleItemDecoration
import com.tongchen.basewidget.adapter.BaseRecyclerAdapter
import com.tongchen.componentservice.module.gank.GankService
import com.tongchen.componentservice.router.ui.RouteManager
import com.tongchen.gank.R
import com.tongchen.gank.base.GankBaseMvpFragment
import com.tongchen.gank.biz.CategoryContract
import com.tongchen.gank.biz.entity.Category
import com.tongchen.gank.biz.entity.GankResult
import com.tongchen.gank.biz.ui.adapter.CategoryAdapter
import com.tongchen.gank.databinding.ModuleGankFragmentCategoryBinding
import kotlinx.android.synthetic.main.module_gank_fragment_category.*

/**
 * @author TongChen
 * @date 2020/11/06  10:20
 * <p>
 * Desc:
 */
class CategoryFragment :
    GankBaseMvpFragment<ModuleGankFragmentCategoryBinding, CategoryContract.Model, CategoryContract.View, CategoryContract.Presenter>(),
    CategoryContract.View {

    private lateinit var mContentAdapter: CategoryAdapter
    private val mData = mutableListOf<GankResult>()

    //  当前所处Tab的分类
    private var mCategory: Category? = null
    private var mTitle: String = "All"
    private var mPage = 1
    private var mSpanCount = 1
    protected lateinit var mContentFragment: Fragment


    companion object {
        internal const val SINGLE_SPAN_COUNT = 1
        internal const val MULTIPLE_SPAN_COUNT = 2

        private const val ARG_CATEGORY = "category"

        fun newInstance(category: Category): CategoryFragment {
            val fragment = CategoryFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_CATEGORY, category)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mCategory = arguments?.getParcelable(ARG_CATEGORY)
            mTitle = mCategory?.mType ?: "All"
        }
    }

    override fun inject2Fragment() {
        gankViewComponent()?.inject(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.module_gank_fragment_category
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mSpanCount = if (mTitle == "Girl") {

            MULTIPLE_SPAN_COUNT
        } else {
            SINGLE_SPAN_COUNT
        }

        smartRefreshLyt.autoRefresh()
        smartRefreshLyt.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                mPage = 1
                refreshData()
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                loadMore()
            }
        })

        val layoutManager = GridLayoutManager(activity, mSpanCount, GridLayoutManager.VERTICAL, false)
        recyclerlv_content.layoutManager = layoutManager
        mContentAdapter = CategoryAdapter(activity!!.applicationContext, mSpanCount, mData)
        recyclerlv_content.adapter = mContentAdapter
        recyclerlv_content.addItemDecoration(SimpleItemDecoration(activity))
        recyclerlv_content.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when (newState) {
                    //  滑动停止后再加载图片
                    RecyclerView.SCROLL_STATE_IDLE -> activity?.let { Glide.with(it).resumeRequests() }
                    //  滑动时暂停加载图片
                    RecyclerView.SCROLL_STATE_DRAGGING,
                    RecyclerView.SCROLL_STATE_SETTLING -> activity?.let { Glide.with(it).pauseRequests() }
                }
            }
        })

        mContentAdapter.setOnItemClickListener(object : BaseRecyclerAdapter.OnItemClickListener {
            override fun onItemClick(itemView: View, position: Int) {
                val itemData = mData[position]
                if (itemData.type == "福利") {
                    mContentFragment = ContentPicFragment.newInstance(itemData)

                } else {
                    mContentFragment = ContentTextFragment.newInstance(itemData)
                }
                val id = RouteManager.navigation(GankService::class.java).getContainerId()
                val mgr = RouteManager.navigation(GankService::class.java).getFragmentMgr()
                startFragment(mgr, id, mContentFragment)
            }
        })

        refreshData()
    }

    override fun refreshData() {
        mPresenter.refreshData("All", mTitle, 10)
    }

    override fun loadMore() {
        mPresenter.loadMoreData("All", mTitle, ++mPage, 10)
    }

    override fun refreshSuccess(result: MutableList<GankResult>?) {
        smartRefreshLyt.finishRefresh(true)

        mData.clear()
        if (result != null) {
            mData.addAll(result)
            mContentAdapter.notifyDataSetChanged()
        }

        LogUtils.d("CategoryFragment", "refreshSucceed---$mCategory, size:${result?.size}")
    }

    override fun refreshFail(msg: String) {
        smartRefreshLyt.finishRefresh(false)

        LogUtils.d("CategoryFragment", "refreshFailed---$msg")
    }

    override fun loadMoreSuccess(result: MutableList<GankResult>?) {
        smartRefreshLyt.finishLoadMore(true)

        if (result != null && result.size > 0) {
            removeIncorrectData(result)
            mData.addAll(result)
            mContentAdapter.notifyDataSetChanged()

        } else {
            ToastUtils.showShort(activity?.applicationContext, R.string.module_gank_no_more)
        }
        LogUtils.d("CategoryFragment", "loadMoreSucceed---${result.toString()}")
    }

    override fun loadMoreFail(msg: String) {
        smartRefreshLyt.finishLoadMore(false)

        LogUtils.d("CategoryFragment", "loadMoreFailed---$msg")
    }

    private fun removeIncorrectData(result: MutableList<GankResult>) {
        val incorrectData = mutableListOf<GankResult>()
        for (element in result) {
            if (element.type == "福利") {
                //  url 不以jpg、jpeg结尾或者包含 7xi8d6.com（此网址挂了）或者来自的 img.gank.io（证书无效）的移除
                if (!(element.url!!.endsWith(".jpg") || element.url!!.endsWith(".jpeg"))
                    || element.url!!.contains("7xi8d6.com")
                    || element.url!!.contains("img.gank.io")
                ) {
                    incorrectData.add(element)
                }
            }
        }
        result.removeAll(incorrectData)
    }

    //  返回到顶部
    fun back2Top() {
        recyclerlv_content.smoothScrollToPosition(0)
    }
}
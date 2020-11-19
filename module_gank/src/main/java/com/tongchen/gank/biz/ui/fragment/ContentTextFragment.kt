package com.tongchen.gank.biz.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.appbar.AppBarLayout
import com.tongchen.baselib.listener.AppBarStateChangeListener
import com.tongchen.componentservice.module.gank.GankService
import com.tongchen.componentservice.router.ui.RouteManager
import com.tongchen.gank.R
import com.tongchen.gank.base.GankBaseDBFragment
import com.tongchen.gank.databinding.ModuleGankFragmentTextBinding
import com.tongchen.gank.biz.entity.GankResult
import kotlinx.android.synthetic.main.module_gank_fragment_text.*

/**
 * @author TongChen
 * @date 2020/01/09  15:09
 * <p>
 * Desc:
 */

@Route(path = "/gank/fragment")
class ContentTextFragment : GankBaseDBFragment<ModuleGankFragmentTextBinding>() {

    private var mGankResult: GankResult? = null

    companion object {
        private const val ARG_GANK_RESULT = "gank_result"

        fun newInstance(result: GankResult): ContentTextFragment {
            val fragment = ContentTextFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_GANK_RESULT, result)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mGankResult = arguments!!.getParcelable(ARG_GANK_RESULT)
        }
    }

    override fun inject2Fragment() {
        dataBindingComponent()?.inject(this)
    }

    override fun getLayoutId(): Int = R.layout.module_gank_fragment_text

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mDataBinding.result = mGankResult

        val imgList = mGankResult?.images

        if (imgList != null&&imgList.isNotEmpty()) {
            appbarLyt.setExpanded(true)

        } else {
            appbarLyt.setExpanded(false)
        }

        appbarLyt.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                when (state) {
                    State.EXPANDED, State.IDLE -> {
                        mtv_title.text = ""
                        collapsingToolbarLyt.title = mGankResult?.desc
                        toolbar.navigationIcon = null
                    }

                    State.COLLAPSED -> {
                        mtv_title.text = mGankResult?.desc
                        collapsingToolbarLyt.title = ""
                        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
                    }
                }
            }
        })

        toolbar.setNavigationOnClickListener {
            val mgr = RouteManager.navigation(GankService::class.java).getFragmentMgr()
            mgr.popBackStack()
        }

        //  先隐藏加载完成后再显示
        tv_publish_date.visibility = View.GONE

        web_content.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                web_content.loadUrl(url)
                return true
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                web_content.loadUrl(request?.url.toString())
                return true
            }
        }

        web_content.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (progressbar_4_web == null) {
                    return
                }
                //  进度条处理
                if (progressbar_4_web.visibility == View.GONE) {
                    progressbar_4_web.visibility = View.VISIBLE
                }
                progressbar_4_web.progress = newProgress
                if (newProgress == 100) {
                    progressbar_4_web.visibility = View.GONE
                    tv_publish_date.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        web_content.loadUrl(mGankResult?.url)
    }

    override fun onStop() {
        super.onStop()
        web_content.stopLoading()
    }

    override fun onBackPressed(): Boolean {
        if (web_content.canGoBack()) {
            web_content.stopLoading()
            web_content.goBack()
            return true
        }
        return super.onBackPressed()
    }

}
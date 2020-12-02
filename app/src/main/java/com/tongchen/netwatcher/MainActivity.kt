package com.tongchen.netwatcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.tongchen.baselib.util.ClickUtils
import com.tongchen.baselib.util.ScreenUtils
import com.tongchen.baselib.util.ToastUtils
import com.tongchen.basemodule.FragmentBackHandler
import com.tongchen.basemodule.base.RootFragment
import com.tongchen.componentservice.module.gank.GankService
import com.tongchen.componentservice.module.mzitu.MZiTuService
import com.tongchen.componentservice.router.ui.RouteManager
import kotlinx.android.synthetic.main.app_activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), FragmentBackHandler {

    private val MAX_CLICK_INTERVAL_TIMEMILLS = 1500

    private var mGankService: GankService? = null
    private var mMZiTuService: MZiTuService? = null
    //  当前展示的模块的根Fragment
    private var mCurrentModuleFragment: Fragment? = null
    private var mIsGank = false
    //  当前展示的最上层的Fragment
    private var mCurrentShowFragment: RootFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity_main)

        mMZiTuService = RouteManager.navigation(MZiTuService::class.java)
        mMZiTuService!!.setContainerId(R.id.fl_fragment_container)
        mMZiTuService!!.setFragmentMgr(supportFragmentManager)

        mGankService = RouteManager.navigation(GankService::class.java)
        mGankService!!.setContainerId(R.id.fl_fragment_container)
        mGankService!!.setFragmentMgr(supportFragmentManager)

        tv_title.setOnClickListener {
            if (ClickUtils.isFastDoubleClick(MAX_CLICK_INTERVAL_TIMEMILLS)) {
                ToastUtils.showShort(applicationContext, "点击过快，请稍后重试！")

            } else {
                switchContent()
            }
        }

        switchContent()
    }

    private fun switchContent() {
        val colorId: Int
        val title: String
        val nextFragment: Fragment
        if (mIsGank) {
            mIsGank = false
            colorId = mMZiTuService!!.getToolbarColor()
            title = getString(mMZiTuService!!.getTitle())
            nextFragment = mMZiTuService!!.getFragment()

        } else {
            mIsGank = true
            colorId = mGankService!!.getToolbarColor()
            title = getString(mGankService!!.getTitle())
            nextFragment = mGankService!!.getFragment()
        }

        tv_title.text = title
        iv_status_bar.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ScreenUtils.getStatusBarHeightByResource(applicationContext))
        iv_status_bar.setBackgroundResource(colorId)
        toolbar.setBackgroundResource(colorId)
        val transaction = supportFragmentManager.beginTransaction()
        if (!nextFragment.isAdded) {
            transaction.add(R.id.fl_module_container, nextFragment)
            mCurrentModuleFragment?.let { transaction.hide(it) }

        } else {
            if (!nextFragment.isVisible) {
                transaction.show(nextFragment)
                mCurrentModuleFragment?.let { transaction.hide(it) }
            }
        }
        transaction.commit()
        mCurrentModuleFragment = nextFragment
    }

    override fun showingFragment(showingFragment: RootFragment?) {
        mCurrentShowFragment = showingFragment
    }

    override fun onBackPressed() {
        //  Fragment消费掉返回事件
        if (mCurrentShowFragment != null && mCurrentShowFragment!!.onBackPressed()) {
            return
        }
        if (supportFragmentManager.backStackEntryCount > 0) {
            super.onBackPressed()
            return
        }

        if (ClickUtils.isFastDoubleClick()) {
            exitProcess(0)

        } else {
            ToastUtils.showShort(applicationContext, R.string.app_sys_exit)
        }
    }

}

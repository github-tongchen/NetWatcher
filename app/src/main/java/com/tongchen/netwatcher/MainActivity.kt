package com.tongchen.netwatcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tongchen.baselib.util.ClickUtils
import com.tongchen.baselib.util.ToastUtils
import com.tongchen.componentservice.module.gank.GankService
import com.tongchen.componentservice.module.mzitu.MZiTuService
import com.tongchen.componentservice.router.ui.RouteManager
import kotlinx.android.synthetic.main.app_activity_main.*

class MainActivity : AppCompatActivity() {

    private val MAX_CLICK_INTERVAL_TIMEMILLS = 1500

    lateinit var mGankService: GankService
    lateinit var mMZiTuService: MZiTuService

    var mLastClickTime: Long = 0
    var mIsGank = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity_main)

        mMZiTuService = RouteManager.navigation(MZiTuService::class.java)
        mGankService = RouteManager.navigation(GankService::class.java)
        iv_switch.setOnClickListener {
            if (ClickUtils.isFastDoubleClick(MAX_CLICK_INTERVAL_TIMEMILLS)) {
                ToastUtils.showShort(applicationContext, "点击过快，请稍后重试！")

            } else {
                switchContent()
            }
        }

        switchContent()
    }

    fun switchContent() {
        val title: String
        val nextFragment: Fragment
        if (mIsGank) {
            mIsGank = false
            iv_status_bar.setBackgroundResource(mMZiTuService.getToolbarColor())
            toolbar.setBackgroundResource(mMZiTuService.getToolbarColor())
            title = getString(mMZiTuService.getTitle())
            nextFragment = mMZiTuService.getFragment()

        } else {
            mIsGank = true
            iv_status_bar.setBackgroundResource(mGankService.getToolbarColor())
            toolbar.setBackgroundResource(mGankService.getToolbarColor())
            title = getString(mGankService.getTitle())
            nextFragment = mGankService.getFragment()
        }

        tv_title.text = title
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_module_container, nextFragment)
        transaction.commit()
    }

}

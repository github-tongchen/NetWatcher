package com.tongchen.netwatcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tongchen.baselib.util.ClickUtils
import com.tongchen.baselib.util.LogUtils
import com.tongchen.baselib.util.ToastUtils
import com.tongchen.componentservice.module.gank.GankService
import com.tongchen.componentservice.module.mzitu.MZiTuService
import com.tongchen.componentservice.router.ui.RouteManager
import kotlinx.android.synthetic.main.app_activity_main.*

class MainActivity : AppCompatActivity() {

    private val MAX_CLICK_INTERVAL_TIMEMILLS = 1500

    private var mGankService: GankService? = null
    private var mMZiTuService: MZiTuService? = null

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
        iv_status_bar.setBackgroundResource(colorId)
        toolbar.setBackgroundResource(colorId)
        val transaction = supportFragmentManager.beginTransaction()
        if (nextFragment.isAdded) {
            transaction.replace(R.id.fl_module_container, nextFragment)

        } else {
            transaction.add(R.id.fl_module_container, nextFragment)
        }
        transaction.commit()
    }

}

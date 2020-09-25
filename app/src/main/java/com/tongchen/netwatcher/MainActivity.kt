package com.tongchen.netwatcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tongchen.componentservice.module.gank.GankService
import com.tongchen.componentservice.module.mzitu.MZiTuService
import com.tongchen.componentservice.router.ui.RouteManager
import kotlinx.android.synthetic.main.app_activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mGankService: GankService
    lateinit var mMZiTuService: MZiTuService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity_main)

        mGankService = RouteManager.navigation(GankService::class.java)
        mMZiTuService = RouteManager.navigation(MZiTuService::class.java)

        tv_title.setText(mGankService.getNameRes())
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_module_container, mGankService.getFragment())
        transaction.commit()
    }
}

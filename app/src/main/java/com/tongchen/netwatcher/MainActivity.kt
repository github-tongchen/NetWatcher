package com.tongchen.netwatcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tongchen.componentservice.module.gank.GankService
import com.tongchen.componentservice.module.mzitu.MZiTuService
import com.tongchen.componentservice.router.ui.RouteManager

class MainActivity : AppCompatActivity() {

    lateinit var mGankService: GankService
    lateinit var mMZiTuService: MZiTuService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity_main)

        mGankService = RouteManager.navigation(GankService::class.java)
        mMZiTuService = RouteManager.navigation(MZiTuService::class.java)

        /*tv_name.setText(mGankService.getNameRes())
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.app_fl_container, mGankService.getFragment())
        transaction.commit()*/
    }
}

package com.tongchen.netwatcher

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.alibaba.android.arouter.launcher.ARouter
import com.tongchen.componentservice.module.gank.GankService
import com.tongchen.componentservice.router.ui.RouteManager
import kotlinx.android.synthetic.main.app_activity_main.*

class MainActivity : AppCompatActivity() {

    var mGankService: GankService?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity_main)

        mGankService = RouteManager.navigation(GankService::class.java)

        tv_name.setText(mGankService!!.getNameRes())
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.app_fl_container, mGankService!!.getFragment())
        transaction.commit()
    }
}

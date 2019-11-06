package com.tongchen.basemodule

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity

/**
 * @author TongChen
 * @date 2019/11/06  11:23
 * <p>
 * Desc:
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())
    }


    @NonNull
    @LayoutRes
    abstract fun getLayoutId(): Int
}
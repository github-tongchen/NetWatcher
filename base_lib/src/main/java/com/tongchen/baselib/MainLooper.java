package com.tongchen.baselib;

import android.os.Handler;
import android.os.Looper;

/**
 * @author TongChen
 * @date 2019/10/20  18:15
 * <p>
 * Desc:
 */
public class MainLooper extends Handler {

    private static MainLooper instance = new MainLooper(Looper.getMainLooper());

    protected MainLooper(Looper looper) {
        super(looper);
    }

    public static MainLooper getInstance() {
        return instance;
    }

    public static void runOnUiThread(Runnable runnable) {
        if(Looper.getMainLooper().equals(Looper.myLooper())) {
            runnable.run();
        } else {
            instance.post(runnable);
        }

    }
}

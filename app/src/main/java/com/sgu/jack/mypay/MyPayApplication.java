package com.sgu.jack.mypay;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by xusha on 2016/8/25.
 */
public class MyPayApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}

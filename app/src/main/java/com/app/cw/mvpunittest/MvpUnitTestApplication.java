package com.app.cw.mvpunittest;

import android.app.Application;

import com.app.cw.mvpunittest.network.NetWorkManager;

public class MvpUnitTestApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetWorkManager.getInstance().init();
    }
}

package com.owen.fragmentbasic;

import android.app.Application;

public class App extends Application {
    private static App APP_INSTANCE;

    public static App getInstance() {
        return APP_INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        APP_INSTANCE = this;
    }
}

package com.eqot.android.app.album;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class AlbumApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();

        LeakCanary.install(this);
    }
}

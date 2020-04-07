package com.liuying.jetpackproject;

import android.app.Application;

import com.liuying.jetpackproject.room.DBInstance;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DBInstance.init(getApplicationContext());
    }
}

package com.example.metcastapp;

import android.app.Application;

import androidx.multidex.MultiDexApplication;

public class App extends Application {
    //private OWService owService;

    @Override
    public void onCreate() {
        super.onCreate();
        //owService = new OWService;
    }

//    public OWService getOwService() {
//        return owService;
//    }
}

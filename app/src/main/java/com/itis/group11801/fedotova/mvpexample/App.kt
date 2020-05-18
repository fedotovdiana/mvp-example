package com.itis.group11801.fedotova.mvpexample

import android.app.Application
import com.itis.group11801.fedotova.mvpexample.di.AppComponent
import com.itis.group11801.fedotova.mvpexample.di.DaggerAppComponent
import moxy.MvpFacade

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        MvpFacade.init()
        appComponent = DaggerAppComponent.builder().application(this).build()
    }
}

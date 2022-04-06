package com.estudo.appestudo.di

import android.app.Application

class MyApplication: Application() {

    lateinit var appContainer: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appContainer = DaggerApplicationComponent.create()
    }

}
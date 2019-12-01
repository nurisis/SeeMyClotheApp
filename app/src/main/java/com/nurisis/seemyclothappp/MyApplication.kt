package com.nurisis.seemyclothappp

import android.app.Application
import com.nurisis.seemyclothappp.di.appModule
import com.nurisis.seemyclothappp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            //            this@GlobalApplication
            androidContext(this@MyApplication)
            modules(listOf(appModule, networkModule))
        }
    }
}
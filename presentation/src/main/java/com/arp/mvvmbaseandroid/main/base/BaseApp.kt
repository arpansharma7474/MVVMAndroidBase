package com.arp.mvvmbaseandroid.main.base

import android.app.Application
import com.arp.mvvmbaseandroid.BuildConfig
import com.arp.mvvmbaseandroid.di.Components.ApplicationComponent
import com.arp.mvvmbaseandroid.di.Components.DaggerApplicationComponent
import com.arp.mvvmbaseandroid.di.Modules.AppModule
import com.arp.mvvmbaseandroid.di.Modules.NetworkModule
import timber.log.Timber

class BaseApp : Application() {

    companion object {
        lateinit var component: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        setUpDaggerModule()
        setUpTimber()

    }

    private fun setUpTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            // Timber.plant(new CrashReportingTree());
        }
    }

    private fun setUpDaggerModule() {
        component = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .build()
    }

}
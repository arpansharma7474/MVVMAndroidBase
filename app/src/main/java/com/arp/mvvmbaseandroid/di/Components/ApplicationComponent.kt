package com.arp.mvvmbaseandroid.di.Components

import android.content.Context
import android.content.SharedPreferences
import com.arp.mvvmbaseandroid.data.network.ApiService
import com.arp.mvvmbaseandroid.di.Modules.AppModule
import com.arp.mvvmbaseandroid.di.Modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface ApplicationComponent {

//    fun inject(activity: BaseActivity)
//
//    fun inject(baseFragment: BaseFragment)
//
//    fun inject(basePresenter: BasePresenterImpl)

    fun context(): Context

    fun sharedPreferences(): SharedPreferences

    fun getApiService(): ApiService

//    fun getCommonUtils(): CommonUtils

}

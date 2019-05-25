package com.arp.mvvmbaseandroid.main.base

import androidx.lifecycle.ViewModel
import com.arp.mvvmbaseandroid.main.base.BaseApp
import com.example.data.network.ApiService
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    @Inject
    lateinit var apiService: ApiService

    init {

        BaseApp.component.inject(this)

    }

}

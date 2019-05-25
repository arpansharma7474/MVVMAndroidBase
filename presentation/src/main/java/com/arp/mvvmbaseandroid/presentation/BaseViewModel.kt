package com.arp.mvvmbaseandroid.presentation

import androidx.lifecycle.ViewModel
import com.example.data.network.ApiService
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    @Inject
    lateinit var apiService: ApiService

    init {


    }

}

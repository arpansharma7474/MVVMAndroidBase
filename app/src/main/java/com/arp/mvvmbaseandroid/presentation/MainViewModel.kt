package com.arp.mvvmbaseandroid.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arp.mvvmbaseandroid.common.Config
import com.arp.mvvmbaseandroid.data.ApiService
import com.arp.mvvmbaseandroid.data.repository.TransactionsRepositoryImpl
import com.arp.mvvmbaseandroid.domain.model.TransactionList
import com.arp.mvvmbaseandroid.domain.usecase.GetTransactionUseCase
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainViewModel : ViewModel() {

    /*required -----------*/
    val transactionObservable by lazy {
        MutableLiveData<TransactionList>()
    }

    private val getTransactionUseCase by lazy {
        GetTransactionUseCase()
    }

    init {

        getTransactionUseCase.userId = "6J8e03xpr5aN2ZPrGivg9uvmG1H2"
        getTransactionUseCase.page = 1

        getTransactionUseCase.execute {
            onComplete {
                transactionObservable.value = it
            }

            onError {
                Log.e("Error", it.message)
            }

            onCancel {
                Log.e("Error", it.localizedMessage)
            }
        }


    }

}
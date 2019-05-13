package com.arp.mvvmbaseandroid.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arp.mvvmbaseandroid.data.usecases.GetTransactionUseCase
import com.arp.mvvmbaseandroid.domain.model.TransactionList


class MainViewModel : ViewModel() {

    val transactionObservable = MutableLiveData<TransactionList>()
    private val getTransactionUseCase = GetTransactionUseCase()

    var userId = ""
    var page = 1


    init {

        getTransactionUseCase.userId = this.userId
        getTransactionUseCase.page = this.page

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
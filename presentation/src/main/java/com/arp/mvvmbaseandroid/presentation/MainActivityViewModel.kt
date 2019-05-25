package com.arp.mvvmbaseandroid.presentation

import androidx.lifecycle.MutableLiveData
import com.example.common.data_classes.TransactionList
import com.example.data.usecases.GetTransactionUseCase
import timber.log.Timber


class MainActivityViewModel : BaseViewModel() {

    val transactionObservable = MutableLiveData<TransactionList>()
    val error = MutableLiveData<Throwable>()

    private val getTransactionUseCase = GetTransactionUseCase(apiService)

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
                Timber.e(it)
            }

            onCancel {
                Timber.e(it.localizedMessage)
            }
        }


    }

    override fun onCleared() {
        super.onCleared()
        getTransactionUseCase.unsubscribe()
    }

}
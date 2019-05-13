package com.arp.mvvmbaseandroid.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arp.mvvmbaseandroid.data.usecases.GetTransactionUseCase
import com.arp.mvvmbaseandroid.domain.model.TransactionList
import timber.log.Timber


class MainActivityViewModel : ViewModel() {

    val transactionObservable = MutableLiveData<TransactionList>()
    val error = MutableLiveData<Throwable>()

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
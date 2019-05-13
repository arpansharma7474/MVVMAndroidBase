package com.arp.mvvmbaseandroid.data.usecases

import com.arp.mvvmbaseandroid.data.repository.TransactionsRepositoryImpl
import com.arp.mvvmbaseandroid.domain.model.TransactionList
import com.arp.mvvmbaseandroid.domain.usecase.UseCase

class GetTransactionUseCase :
    UseCase<TransactionList>() {

    var page = -1
    var userId = ""

    private val transactionRepository by lazy {
        TransactionsRepositoryImpl(apiService)
    }

    override suspend fun executeOnBackground(): TransactionList {
        return transactionRepository.getTransactions(userId, page)
    }
}
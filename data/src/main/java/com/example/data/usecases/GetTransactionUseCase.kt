package com.example.data.usecases

import com.example.data.network.ApiService
import com.example.data.repository.TransactionsRepositoryImpl
import com.example.domain.model.TransactionList
import com.example.domain.usecase.UseCase

class GetTransactionUseCase(val apiService: ApiService) :
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
package com.example.data.usecases

import com.example.common.data_classes.TransactionList
import com.example.data.network.ApiService
import com.example.data.repository.TransactionsRepositoryImpl
import com.example.domain.usecase.UseCase

class GetTransactionUseCase(private val apiService: ApiService) :
    UseCase<TransactionList>() {

    var page = -1
    var userId = "Y5bmFQUKYwOJOGDeZ6NErWfvrqV2"

    private val transactionRepository by lazy {
        TransactionsRepositoryImpl(apiService)
    }

    override suspend fun executeOnBackground(): TransactionList {
        return transactionRepository.getTransactions(userId, page)
    }
}
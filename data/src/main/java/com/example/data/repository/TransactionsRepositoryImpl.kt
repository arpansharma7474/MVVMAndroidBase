package com.example.data.repository

import com.example.common.data_classes.TransactionList
import com.example.data.network.ApiService
import com.example.domain.repository.TransactionRepository

class TransactionsRepositoryImpl(private val apiService: ApiService) : TransactionRepository {

    override suspend fun getTransactions(userId: String, page: Int): TransactionList {
        return apiService.getTransactionsAsync(userId, page).await()
    }

}
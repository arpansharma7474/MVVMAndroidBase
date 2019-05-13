package com.arp.mvvmbaseandroid.data.repository

import com.arp.mvvmbaseandroid.data.network.ApiService
import com.arp.mvvmbaseandroid.domain.model.TransactionList
import com.arp.mvvmbaseandroid.domain.repository.TransactionRepository

class TransactionsRepositoryImpl(private val apiService: ApiService) : TransactionRepository {

    override suspend fun getTransactions(userId: String, page: Int): TransactionList {
        return apiService.getTransactionsAsync(userId, page).await()
    }

}
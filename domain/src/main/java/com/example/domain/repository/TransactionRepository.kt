package com.example.domain.repository

import com.example.domain.model.TransactionList

interface TransactionRepository {

    suspend fun getTransactions(userId: String, page: Int = 1): TransactionList

}
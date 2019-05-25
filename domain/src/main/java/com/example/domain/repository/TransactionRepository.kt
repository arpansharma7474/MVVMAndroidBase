package com.example.domain.repository

import com.example.common.data_classes.TransactionList


interface TransactionRepository {

    suspend fun getTransactions(userId: String, page: Int = 1): TransactionList

}
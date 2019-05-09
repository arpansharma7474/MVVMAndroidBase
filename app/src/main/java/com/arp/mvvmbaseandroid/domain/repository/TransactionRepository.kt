package com.arp.mvvmbaseandroid.domain.repository

import com.arp.mvvmbaseandroid.domain.model.TransactionList

interface TransactionRepository {

    suspend fun getTransactions(userId: String, page: Int = 1): TransactionList

}
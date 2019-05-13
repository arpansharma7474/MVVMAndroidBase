package com.arp.mvvmbaseandroid.data.usecases

import com.arp.mvvmbaseandroid.common.Config
import com.arp.mvvmbaseandroid.data.network.ApiService
import com.arp.mvvmbaseandroid.data.repository.TransactionsRepositoryImpl
import com.arp.mvvmbaseandroid.domain.model.TransactionList
import com.arp.mvvmbaseandroid.domain.usecase.UseCase
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
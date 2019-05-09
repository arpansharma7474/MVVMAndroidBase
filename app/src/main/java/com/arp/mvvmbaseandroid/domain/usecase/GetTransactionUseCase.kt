package com.arp.mvvmbaseandroid.domain.usecase

import com.arp.mvvmbaseandroid.common.Config
import com.arp.mvvmbaseandroid.data.ApiService
import com.arp.mvvmbaseandroid.data.repository.TransactionsRepositoryImpl
import com.arp.mvvmbaseandroid.domain.model.TransactionList
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetTransactionUseCase :
    UseCase<TransactionList>() {

    var page = -1
    var userId = ""

    /*Can be injected ---------------*/
    private val apiService by lazy {
        val build = Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okhttp)
            .build()
        build.create(ApiService::class.java)
    }

    /*Can be injected ---------------*/
    private val okhttp by lazy {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient().newBuilder().addInterceptor(httpLoggingInterceptor).build()
    }

    private val transactionRepository = TransactionsRepositoryImpl(apiService)

    override suspend fun executeOnBackground(): TransactionList {
        return transactionRepository.getTransactions(userId, page)
    }
}
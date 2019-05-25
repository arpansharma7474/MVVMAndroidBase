package com.example.data.network

import com.example.domain.model.TransactionList
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("transactions/")
    fun getTransactionsAsync(@Query("userId") userId: String, @Query("page") page: Int): Deferred<TransactionList>

}
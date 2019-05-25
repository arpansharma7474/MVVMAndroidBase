package com.arp.mvvmbaseandroid.di.Modules

import android.content.SharedPreferences
import com.arp.mvvmbaseandroid.BuildConfig
import com.arp.mvvmbaseandroid.common.Config
import com.arp.mvvmbaseandroid.common.SecurityInterceptor
import com.example.data.network.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
class NetworkModule {

    @Singleton
    @Provides
    internal fun getRetrofit(
        @Named("baseUrl") baseUrl: String, okHttpClient: OkHttpClient,
        factory: Converter.Factory,
        coroutineCallAdapterFactory: CoroutineCallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(coroutineCallAdapterFactory)
            .addConverterFactory(factory)
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Named("baseUrl")
    @Provides
    internal fun getBaseUrl(): String {
        return Config.BASE_URL
    }

    @Singleton
    @Provides
    internal fun getGSONConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    internal fun getCoroutineAdapterFactory(): CoroutineCallAdapterFactory {
        return CoroutineCallAdapterFactory()
    }

    @Singleton
    @Provides
    internal fun getOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val builder = OkHttpClient.Builder().addInterceptor(interceptor)
        builder.readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor { message -> Timber.d(message) }
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(httpLoggingInterceptor)
        }
        return builder.build()
    }


    @Singleton
    @Provides
    internal fun getGsonFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    internal fun getApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    internal fun getSecurityInterceptor(userPref: SharedPreferences): Interceptor {
        return SecurityInterceptor(userPref)
    }


}
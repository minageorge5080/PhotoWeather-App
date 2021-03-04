package com.minaroid.photoweather.di

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.minaroid.photoweather.BuildConfig
import com.minaroid.photoweather.data.remote.ApiService
import com.minaroid.photoweather.data.remote.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideInterceptor(): Interceptor {
        return Interceptor.invoke { chain ->
            val url = chain.request().url.newBuilder()
                .addQueryParameter("appid", BuildConfig.APP_ID)
                .addQueryParameter("units", "metric")
                .build()

            val request = chain.request().newBuilder()
                .url(url)
                .build()

            chain.proceed(request)
        }
    }

    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                readTimeout(20L, TimeUnit.SECONDS)
                writeTimeout(20L, TimeUnit.SECONDS)
                callTimeout(20L, TimeUnit.SECONDS)
                writeTimeout(20L, TimeUnit.SECONDS)
                addInterceptor(interceptor)

                if (BuildConfig.DEBUG) {
                    val loggingInterceptor = HttpLoggingInterceptor()
                    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    addInterceptor(loggingInterceptor)
                }

            }.build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.newThread()))
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideNetworkManagerHelper(apiService: ApiService): NetworkManager {
        return NetworkManager(apiService)
    }

}
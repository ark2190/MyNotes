package com.mynotes.core.di

import android.app.Application
import com.fasterxml.jackson.databind.ObjectMapper
import com.mynotes.BuildConfig
import com.mynotes.core.api.ApiManager
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

/**
 * Created by Anurag on 25-03-2018.
 */
@Module(includes = arrayOf(AppModule::class))
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideHttpCache(application: Application): Cache {
        val cacheSize: Long = 10 * 1024 * 1024 //10 mb cache size
        return Cache(application.cacheDir, cacheSize)
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
                .cache(cache)
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient, objectMapper: ObjectMapper): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .baseUrl(BuildConfig.API_URL)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideApiManager(retrofit: Retrofit) = ApiManager(retrofit)
}
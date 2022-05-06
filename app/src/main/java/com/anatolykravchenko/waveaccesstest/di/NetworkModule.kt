package com.anatolykravchenko.waveaccesstest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.anatolykravchenko.waveaccesstest.data.network.ApiService

object NetworkModule {
    @Module
    @InstallIn(SingletonComponent::class)
    object AppModule {

        @Provides
        @Singleton
        fun provideRetrofit () = Retrofit.Builder()
            .baseUrl("https://api.openbrewerydb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        @Provides
        @Singleton
        fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    }


}
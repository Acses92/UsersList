package com.anatolykravchenko.waveaccesstest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.anatolykravchenko.waveaccesstest.data.network.ApiService
import com.anatolykravchenko.waveaccesstest.domain.UserNetworkRepository
import com.anatolykravchenko.waveaccesstest.data.network.UserNetworkRepositoryImpl

/**
 * DI модуля сетевого взаимодействия
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
        @Provides
        @Singleton
        fun provideRetrofit () = Retrofit.Builder()
            .baseUrl("https://firebasestorage.googleapis.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        @Provides
        @Singleton
        fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

        @Provides
        @Singleton
        fun getUserRepository(apiService: ApiService): UserNetworkRepository =
                UserNetworkRepositoryImpl(apiService)
 }

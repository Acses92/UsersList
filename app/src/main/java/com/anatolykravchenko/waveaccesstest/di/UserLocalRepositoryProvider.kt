package com.anatolykravchenko.waveaccesstest.di

import android.content.Context
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.anatolykravchenko.waveaccesstest.domain.UserLocalRepository
import com.anatolykravchenko.waveaccesstest.data.database.UserLocalRepositoryImpl
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * DI репозитория БД
 */
@Module
@InstallIn(SingletonComponent::class)
object UserLocalRepositoryProvider {

    @Provides
    @Singleton
    fun getRepository(@ApplicationContext context: Context): UserLocalRepository {
        return UserLocalRepositoryImpl(DatabaseProvider.getDb(context).userDao)

    }
}
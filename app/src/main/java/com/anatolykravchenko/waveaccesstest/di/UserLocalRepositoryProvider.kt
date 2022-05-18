package com.anatolykravchenko.waveaccesstest.di

import android.content.Context
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.anatolykravchenko.waveaccesstest.domain.UserLocalRepository
import com.anatolykravchenko.waveaccesstest.data.database.UserLocalRepositoryImpl
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * DI репозитория БД
 */
@Module
@InstallIn(SingletonComponent::class)
object UserLocalRepositoryProvider {
    private var repository: UserLocalRepository? = null

    @Provides
    fun getRepository(@ApplicationContext context: Context): UserLocalRepository {
        return repository ?:
        UserLocalRepositoryImpl(DatabaseProvider.getDb(context).userDao)
            .also { repository = it }
    }
}
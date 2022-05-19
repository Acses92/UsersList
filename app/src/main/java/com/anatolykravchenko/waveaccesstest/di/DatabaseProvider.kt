package com.anatolykravchenko.waveaccesstest.di

import android.content.Context
import com.anatolykravchenko.waveaccesstest.data.database.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * DI локальной базы данных
 *
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseProvider {
    @Provides
    @Singleton
    fun getDb(context: Context): LocalDatabase {
        return  LocalDatabase.create(context)
    }
}
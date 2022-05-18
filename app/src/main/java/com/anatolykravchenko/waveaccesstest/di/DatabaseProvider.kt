package com.anatolykravchenko.waveaccesstest.di

import android.content.Context
import com.anatolykravchenko.waveaccesstest.data.database.LocalDatabase

/**
 * DI локальной базы данных
 */
object DatabaseProvider {
    private var db: LocalDatabase? = null

    fun getDb(context: Context): LocalDatabase {
        return db?: LocalDatabase.create(context).also { db = it }
    }
}
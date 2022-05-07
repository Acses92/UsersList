package com.anatolykravchenko.waveaccesstest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anatolykravchenko.waveaccesstest.data.model.UserItemDb

@Database(
    entities = [UserItemDb::class],
    version = 1,
    exportSchema = false
)

abstract class LocalDatabase: RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        fun create(context: Context): LocalDatabase =
        Room.databaseBuilder(
            context,
            LocalDatabase::class.java,
            "local_database")
            .build()
    }
}
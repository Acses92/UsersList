package com.anatolykravchenko.waveaccesstest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anatolykravchenko.waveaccesstest.data.database.entity.UserItemEntity

/**
 * База данных
 */
@Database(
    entities = [UserItemEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListToStringConverter::class, StringToFriendConverter::class)
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
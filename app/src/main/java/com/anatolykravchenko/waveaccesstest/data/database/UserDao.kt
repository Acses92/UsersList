package com.anatolykravchenko.waveaccesstest.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anatolykravchenko.waveaccesstest.data.model.UserItemDb

@Dao
interface UserDao {
    /**
     *Получает список всех пользователей
     */
    @Query("SELECT * FROM user_item_local")
    suspend fun getAll(): List<UserItemDb>

    /**
     * Удаляет список всех пользователей
     */
    @Query("DELETE FROM user_item_local")
    suspend fun dellAll()

    /**
     * Добавляет список пользователей
     */
    @Insert
    suspend fun insert(userItemDb: UserItemDb)

}
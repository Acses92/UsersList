package com.anatolykravchenko.waveaccesstest.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anatolykravchenko.waveaccesstest.data.database.entity.UserItemEntity

@Dao
interface UserDao {
    /**
     *Получает список всех пользователей
     */
    @Query("SELECT * FROM user_item_local")
    suspend fun getAll(): List<UserItemEntity>

    /**
     * Удаляет всех пользователей
     */
    @Query("DELETE FROM user_item_local")
    suspend fun dellAll()

    /**
     * Добавляет список пользователей
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userItemEntity: List<UserItemEntity>)

    /**
     * Получаем список пользователей по ID
     */
    @Query("SELECT * FROM user_item_local WHERE id = :id")
    suspend fun getById(id: Int): UserItemEntity

}
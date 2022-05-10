package com.anatolykravchenko.waveaccesstest.domain

import com.anatolykravchenko.waveaccesstest.data.model.UserItemEntity
import com.anatolykravchenko.waveaccesstest.data.model.UserItemUi


interface UserLocalRepository {
    /**
     *Получает список всех пользователей
     */
    suspend fun getAll(): List<UserItemUi>

    /**
     * Удаляет всех пользователей
     */
    suspend fun dellAll()

    /**
     * Добавляет список пользователей
     */
    suspend fun insert(userItemDb: UserItemEntity)
}
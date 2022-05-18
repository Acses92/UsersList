package com.anatolykravchenko.waveaccesstest.domain

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
    suspend fun insert(userItemUi: List<UserItemUi>)

    /**
     * Получает пользователя по ID
     */
    suspend fun getUserById(id: Int): UserItemUi
}
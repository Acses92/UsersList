package com.anatolykravchenko.waveaccesstest.domain

import com.anatolykravchenko.waveaccesstest.data.model.UserItemDto

interface UserNetworkRepository {
    /**
     * Функция получения списка User
     */
    suspend fun getUserList(): ArrayList<UserItemDto>

}
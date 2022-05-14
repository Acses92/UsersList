package com.anatolykravchenko.waveaccesstest.data.network

import com.anatolykravchenko.waveaccesstest.data.model.UserItemDto
import com.anatolykravchenko.waveaccesstest.domain.UserNetworkRepository
import javax.inject.Inject

class UserNetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): UserNetworkRepository {
    /**
     *Получает массив пользователей
     */
    override suspend fun getUserList(): ArrayList<UserItemDto> = apiService.getUsersList()
}
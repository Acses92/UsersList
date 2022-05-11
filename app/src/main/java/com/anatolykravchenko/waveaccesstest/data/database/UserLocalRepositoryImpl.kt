package com.anatolykravchenko.waveaccesstest.data.database

import com.anatolykravchenko.waveaccesstest.data.model.UserItemEntity
import com.anatolykravchenko.waveaccesstest.data.model.UserItemUi
import com.anatolykravchenko.waveaccesstest.domain.UserLocalRepository

class UserLocalRepositoryImpl(
    private val userDao: UserDao
): UserLocalRepository    {
    override suspend fun getAll(): List<UserItemUi> {
        TODO("Not implemented")
    }

    override suspend fun dellAll() {
        TODO("Not yet implemented")
    }

    override suspend fun insert(userItemDb: UserItemEntity) {
        TODO("Not yet implemented 2")
    }
}
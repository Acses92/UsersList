package com.anatolykravchenko.waveaccesstest.data.database

import com.anatolykravchenko.waveaccesstest.data.model.UserItemUi
import com.anatolykravchenko.waveaccesstest.domain.UserLocalRepository

class UserLocalRepositoryImpl(
    private val userDao: UserDao
): UserLocalRepository    {
    override suspend fun getAll(): List<UserItemUi> =
        userDao.getAll().map{ it.toUserItemUi()}

    override suspend fun dellAll() = userDao.dellAll()

    override suspend fun insert(userItemUi: List<UserItemUi>) {
        userDao.insert(userItemUi.map { it.toUserItemEntity() })
    }

    override suspend fun getUserById(id: Int): List<UserItemUi> =
        userDao.getById(id).map {it.toUserItemUi()}

}
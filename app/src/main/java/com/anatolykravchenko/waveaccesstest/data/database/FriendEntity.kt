package com.anatolykravchenko.waveaccesstest.data.database

import androidx.room.Entity

@Entity(tableName = "user_friend_db", primaryKeys = ["id"])
data class FriendEntity(
    val id: Int
)
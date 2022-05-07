package com.anatolykravchenko.waveaccesstest.data.model

import androidx.room.Entity

@Entity(tableName = "user_friend_db", primaryKeys = ["id"])
data class FriendDb(
    val id: Int
)
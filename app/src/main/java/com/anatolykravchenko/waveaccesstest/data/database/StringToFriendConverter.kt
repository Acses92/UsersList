package com.anatolykravchenko.waveaccesstest.data.database

import androidx.room.TypeConverter
import com.anatolykravchenko.waveaccesstest.data.model.Friend

class StringToFriendConverter {

    @TypeConverter
    fun friendToString(friend: List<Friend>): String {
        return friend.map{it.id.toString()}.toString()
    }

    @TypeConverter
    fun fromStringToFriends(value: String?): List<Friend>? {
        return value
            ?.split(",")
            ?.mapNotNull { Friend(it.toIntOrNull() ?: return@mapNotNull null) }
    }

}
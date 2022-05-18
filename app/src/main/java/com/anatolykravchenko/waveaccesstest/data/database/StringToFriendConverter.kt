package com.anatolykravchenko.waveaccesstest.data.database

import androidx.room.TypeConverter
import com.anatolykravchenko.waveaccesstest.data.model.Friend

/**
 * Кновертер string в List<Friend> в String, String в List<Friend>
 */
class StringToFriendConverter {

    @TypeConverter
    fun friendToString(friend: List<Friend>): String {
        return friend.joinToString(separator = ",", transform = { it.id.toString() })
    }

    @TypeConverter
    fun fromStringToFriends(value: String?): List<Friend>? {
        return value
            ?.split(",")
            ?.mapNotNull { Friend(it.toIntOrNull() ?: return@mapNotNull null) }
    }

}
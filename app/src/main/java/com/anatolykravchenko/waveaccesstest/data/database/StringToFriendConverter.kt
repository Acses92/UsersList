package com.anatolykravchenko.waveaccesstest.data.database

import androidx.room.TypeConverter
import com.anatolykravchenko.waveaccesstest.data.model.Friend

class StringToFriendConverter {

    @TypeConverter
    fun friendToString(friend: List<Friend>): String {
        return friend.map{it.id.toString()}.toString()
    }

    @TypeConverter
    fun stringToFriend(string:String): List<Friend> {
        val result = string.replace("]","").replace("[","")
            .replace(" ", "").split(",")
        return result.map { Friend(it.toInt()) }
    }
}
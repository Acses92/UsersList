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
        val res = string.replace(" ", "").replace(",","")
            .replace("[","").replace("]","").map{
            it.toString().toInt()
        }
        return res.map{Friend(it.toInt())}.toList()
    }
}
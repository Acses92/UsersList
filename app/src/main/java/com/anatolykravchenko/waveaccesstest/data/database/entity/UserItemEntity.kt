package com.anatolykravchenko.waveaccesstest.data.database.entity

import androidx.room.Entity
import androidx.room.TypeConverters
import com.anatolykravchenko.waveaccesstest.data.database.ListToStringConverter
import com.anatolykravchenko.waveaccesstest.data.model.UserItemUi

@Entity(tableName = "user_item_local", primaryKeys = ["id"])
data class UserItemEntity(
    val about: String,
    val address: String,
    val age: Int,
    val balance: String,
    val company: String,
    val email: String,
    val eyeColor: String,
    val favoriteFruit: String,
    //было val friends: List<Friend>
    val friends: String,
    val gender: String,
    val guid: String,
    val id: Int,
    val isActive: Boolean,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val phone: String,
    val registered: String,
    //было val tags: List<String>
    @TypeConverters(ListToStringConverter::class)
    val tags: List<String>
) {
    fun toUserItemUi() = UserItemUi(
        about,
        address,
        age,
        balance,
        company,
        email,
        eyeColor,
        favoriteFruit,
        friends,
        gender,
        guid,
        id,
        isActive,
        latitude,
        longitude,
        name,
        phone,
        registered,
        tags
    )
}
package com.anatolykravchenko.waveaccesstest.data.model

import android.os.Parcelable
import com.anatolykravchenko.waveaccesstest.data.database.entity.UserItemEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserItemUi(
    val about: String,
    val address: String,
    val age: Int,
    val balance: String,
    val company: String,
    val email: String,
    val eyeColor: String,
    val favoriteFruit: String,
    val friends: List<Friend>,
    val gender: String,
    val guid: String,
    val id: Int,
    val isActive: Boolean,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val phone: String,
    val registered: String,
    val tags: List<String>
): Parcelable
{
    fun toUserItemEntity() = UserItemEntity(
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
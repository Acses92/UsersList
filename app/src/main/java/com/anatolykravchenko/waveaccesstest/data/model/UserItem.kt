package com.anatolykravchenko.waveaccesstest.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserItem(
    @SerialName("about")
    val about: String,
    @SerialName("address")
    val address: String,
    @SerialName("age")
    val age: Int,
    @SerialName("balance")
    val balance: String,
    @SerialName("company")
    val company: String,
    @SerialName("email")
    val email: String,
    @SerialName("eyeColor")
    val eyeColor: String,
    @SerialName("favoriteFruit")
    val favoriteFruit: String,
    @SerialName("friends")
    val friends: List<Friend>,
    @SerialName("gender")
    val gender: String,
    @SerialName("guid")
    val guid: String,
    @SerialName("id")
    val id: Int,
    @SerialName("isActive")
    val isActive: Boolean,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("name")
    val name: String,
    @SerialName("phone")
    val phone: String,
    @SerialName("registered")
    val registered: String,
    @SerialName("tags")
    val tags: List<String>
)
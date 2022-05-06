package com.anatolykravchenko.waveaccesstest.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Friend(
    @SerialName("id")
    val id: Int
)
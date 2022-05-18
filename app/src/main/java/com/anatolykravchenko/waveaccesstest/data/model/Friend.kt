package com.anatolykravchenko.waveaccesstest.data.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
/**
 * Хранение Friend
 */
@Serializable
@Parcelize
data class Friend(
    @SerialName("id")
    val id: Int
):Parcelable

package com.anatolykravchenko.waveaccesstest.data.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Friend(
    @SerialName("id")
    val id: Int
):Parcelable

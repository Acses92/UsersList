package com.anatolykravchenko.waveaccesstest.data.model


import androidx.room.TypeConverter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Friend(
    @SerialName("id")
    val id: Int
) {
    internal class FriendsConverter {

        @TypeConverter
        fun fromStringToFriends(value: String?): List<Friend>? {
            return value
                ?.split(SEPARATOR)
                ?.mapNotNull { Friend(it.toIntOrNull() ?: return@mapNotNull null) }
        }

        @TypeConverter
        fun fromFriendsToString(friends: List<Friend>?): String? {
            return friends?.joinToString(separator = SEPARATOR, transform = { it.id.toString() })
        }

        companion object {

            private const val SEPARATOR = ","
        }
    }
}
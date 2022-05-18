package com.anatolykravchenko.waveaccesstest.data.database

import androidx.room.TypeConverter
/**
 * Конвертер String в list<String>, List<String> в String
 */
class ListToStringConverter {
    @TypeConverter
    fun fromStringToList(string: String): List<String> {
        return string.split(",").map{it.trim()}
    }
    @TypeConverter
    fun fromListToString(list: List<String>): String {
        return list.joinToString(separator = ",")
    }
}
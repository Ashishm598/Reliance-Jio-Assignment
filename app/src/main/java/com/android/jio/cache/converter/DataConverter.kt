package com.android.jio.cache.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.android.jio.model.Data
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DataConverter {

    @TypeConverter
    fun fromStringToListData(value: String): List<Data> {
        return Gson().fromJson(value, Array<Data>::class.java).toList()
    }

    @TypeConverter
    fun fromListDataToString(data: List<Data>): String {
        return Gson().toJson(data)
    }

}
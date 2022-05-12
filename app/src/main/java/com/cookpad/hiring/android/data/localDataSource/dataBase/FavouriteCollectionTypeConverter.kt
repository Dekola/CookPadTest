package com.cookpad.hiring.android.data.localDataSource.dataBase

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class FavouriteCollectionTypeConverter {
    var gson = Gson()

    @TypeConverter
    fun fromListToString(list: List<String>): String {
        val type = object: TypeToken<List<String>>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toData(dataString: String?): List<String> {
        if(dataString == null || dataString.isEmpty()) {
            return mutableListOf()
        }
        val type: Type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(dataString, type)
    }

}
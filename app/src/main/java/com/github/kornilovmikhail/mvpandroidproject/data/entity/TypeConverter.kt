package com.github.kornilovmikhail.mvpandroidproject.data.entity

import androidx.room.TypeConverter
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Answer
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson


class TypeConverter {

    @TypeConverter
    fun fromString(value: String): ArrayList<Answer> {
        val listType = object : TypeToken<ArrayList<Answer>>() {
        }.type
        return Gson().fromJson<ArrayList<Answer>>(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<Answer>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
package com.example.stopbadhabit.data.model.Habit

import androidx.room.TypeConverter
import com.example.stopbadhabit.data.model.Diary.Diary
import com.google.gson.Gson

class MyTypeConverter {
    @TypeConverter
    fun fromStringList(value: List<Diary>?): String = Gson().toJson(value)

    @TypeConverter
    fun toStringList(value: String?) = Gson().fromJson(value, Array<Diary>::class.java).toList()
}
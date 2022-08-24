package com.example.stopbadhabit.data.model.Diary

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Diary(
    @PrimaryKey(autoGenerate = true)
    val diary_id: Int,

    @ColumnInfo(name = "habit_id")
    val habit_id: Int,

    @ColumnInfo(name = "diary_date")
    val diary_date: Date,

    @ColumnInfo(name = "situation")
    val situation: String?,

    @ColumnInfo(name = "reason")
    val reason: String?,

    @ColumnInfo(name = "emotion")
    val emotion: String?,

    @ColumnInfo(name = "promise")
    val promise: String

)


/*
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val habit_id: Int,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "goal_date")
    val goal_date: Int?,

    @ColumnInfo(name = "start_date")
    val start_date: Date?,

    @ColumnInfo(name = "end_date")
    val end_date: Date?,

    @ColumnInfo(name= "current_life")
    val current_life : Int,

    @ColumnInfo(name= "setting_life")
    val setting_life : Int,

    @ColumnInfo(name = "state")
    val state: Int

)*/

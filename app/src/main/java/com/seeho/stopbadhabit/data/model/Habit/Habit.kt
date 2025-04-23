package com.seeho.stopbadhabit.data.model.Habit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val habit_id: Int? = null,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "goal_date")
    val goal_date: Int,

    @ColumnInfo(name = "start_date")
    val start_date: String,

    @ColumnInfo(name = "end_date")
    val end_date: String? = null,

    @ColumnInfo(name= "current_life")
    val current_life : Int,

    @ColumnInfo(name= "setting_life")
    val setting_life : Int,

    @ColumnInfo(name = "state")
    val state: Int,

    @ColumnInfo(name ="mode")
    val mode: Int,

    @ColumnInfo(name="notification_time")
    val notification_time : String? = null

    )



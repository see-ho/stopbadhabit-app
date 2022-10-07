package com.seeho.stopbadhabit.data.model.Diary

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.seeho.stopbadhabit.data.model.Habit.Habit

@Entity ( tableName = "diary",
    foreignKeys = [
        ForeignKey(
            entity = Habit::class,
            parentColumns = ["habit_id"],
            childColumns = ["habit_id"]
        )
    ]
)
data class Diary(
    @PrimaryKey(autoGenerate = true)
    val diary_id: Int?=null,

    @ColumnInfo(name = "habit_id")
    val habit_id : Int,

    @ColumnInfo(name = "diary_date")
    val diary_date: String,

    @ColumnInfo(name = "situation")
    val situation: String?,

    @ColumnInfo(name = "reason")
    val reason: String?,

    @ColumnInfo(name = "emotion")
    val emotion: String?,

    @ColumnInfo(name = "promise")
    val promise: String,

    @ColumnInfo(name ="img_res")
    val img_res: Int,

)

package com.example.stopbadhabit.data.model.Habit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.example.stopbadhabit.data.model.Diary.Diary
import java.util.*

@Entity(
    tableName = "Habit", foreignKeys = [
        ForeignKey(
            entity = Diary::class,
            parentColumns = ["habit_id"],
            childColumns = ["habit_id"],
            onDelete = CASCADE
        )
    ]
)

data class Habit(
    @PrimaryKey(autoGenerate = true)
    val habit_id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "goal_date")
    val goal_date: Int,

    @ColumnInfo(name = "start_date")
    val start_date: Date,

    @ColumnInfo(name = "end_date")
    val end_date: Date,

    @ColumnInfo(name= "current_life")
    val current_life : Int,

    @ColumnInfo(name= "setting_life")
    val setting_life : Int,

    @ColumnInfo(name = "state")
    val state: Int

    )



package com.seeho.stopbadhabit.data.model.Battle

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.seeho.stopbadhabit.data.model.Habit.Habit


@Entity ( tableName = "battle",
    foreignKeys = [
        ForeignKey(
            entity = Habit::class,
            parentColumns = ["habit_id"],
            childColumns = ["habit_id"]
        )
    ],
    indices = [
        Index("habit_id")
    ]
)

data class Battle(
    @PrimaryKey(autoGenerate = true)
    val battle_id: Int? = null,

    @ColumnInfo(name = "habit_id")
    val habit_id : Int,

    @ColumnInfo(name = "battle_date")
    val battle_date: String,

    @ColumnInfo(name = "is_success")
    val is_success: Boolean
)

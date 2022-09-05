package com.example.stopbadhabit.data.model.HabitAndModel

import androidx.room.Embedded
import androidx.room.Relation
import com.example.stopbadhabit.data.model.Diary.Diary
import com.example.stopbadhabit.data.model.Habit.Habit

data class HabitAndDiary(
    @Embedded val habit: Habit,
    @Relation(
        parentColumn = "habit_id",
        entityColumn = "habit_id"
    )
    val diaries: List<Diary>?
)

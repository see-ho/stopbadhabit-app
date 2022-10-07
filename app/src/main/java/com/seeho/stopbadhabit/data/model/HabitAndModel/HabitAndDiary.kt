package com.seeho.stopbadhabit.data.model.HabitAndModel

import androidx.room.Embedded
import androidx.room.Relation
import com.seeho.stopbadhabit.data.model.Diary.Diary
import com.seeho.stopbadhabit.data.model.Habit.Habit

data class HabitAndDiary(
    @Embedded val habit: Habit,
    @Relation(
        parentColumn = "habit_id",
        entityColumn = "habit_id"
    )
    val diaries: List<Diary>?
)

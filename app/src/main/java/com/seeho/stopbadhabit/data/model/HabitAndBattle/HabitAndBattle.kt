package com.seeho.stopbadhabit.data.model.HabitAndBattle

import androidx.room.Embedded
import androidx.room.Relation
import com.seeho.stopbadhabit.data.model.Battle.Battle
import com.seeho.stopbadhabit.data.model.Habit.Habit

data class HabitAndBattle(
    @Embedded val habit: Habit,
    @Relation(
        parentColumn = "habit_id",
        entityColumn = "habit_id"
    )
    val battles: List<Battle>?)

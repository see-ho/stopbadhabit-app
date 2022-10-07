package com.seeho.stopbadhabit.data.model.PresentHabit

import com.seeho.stopbadhabit.data.model.Habit.Habit

data class PresentHabit (
    val habit: Habit,
    val status: Int,
)
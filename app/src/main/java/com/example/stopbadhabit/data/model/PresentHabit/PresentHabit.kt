package com.example.stopbadhabit.data.model.PresentHabit

import com.example.stopbadhabit.data.model.Habit.Habit

data class PresentHabit (
    val habit: Habit,
    val status: Int,
)
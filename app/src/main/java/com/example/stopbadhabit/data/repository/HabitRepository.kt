package com.example.stopbadhabit.data.repository

import androidx.lifecycle.LiveData
import com.example.stopbadhabit.data.model.Habit.Habit
import com.example.stopbadhabit.data.model.Habit.HabitDao
import dagger.Module

class HabitRepository(private val habitDao: HabitDao) {
    fun getHomeHabits() : List<Habit> {
        return habitDao.getHomeHabits()
    }

    fun insertHabit(habit: Habit){
        habitDao.insertHabit(habit)
    }

    suspend fun deleteAll(){
        habitDao.deleteAll()
    }

    suspend fun getHabitById(habitId:Int) : Habit? {
        return habitDao.getHabitById(habitId)
    }
}
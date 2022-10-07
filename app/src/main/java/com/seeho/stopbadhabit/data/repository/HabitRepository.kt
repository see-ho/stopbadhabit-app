package com.seeho.stopbadhabit.data.repository

import com.seeho.stopbadhabit.data.model.Habit.Habit
import com.seeho.stopbadhabit.data.model.Habit.HabitDao
import com.seeho.stopbadhabit.data.model.PresentHabit.PresentHabit
import com.seeho.stopbadhabit.util.toPresentHabit

class HabitRepository(private val habitDao: HabitDao) {


    fun getHomeHabits() : List<PresentHabit> {
        return habitDao.getHomeHabits().map {
            it.toPresentHabit()
        }
    }

    fun insertHabit(habit: Habit){
        habitDao.insertHabit(habit)
    }


    suspend fun getHabitById(habitId:Int) : Habit? {
        return habitDao.getHabitById(habitId)
    }

    suspend fun updateHabit(habit: Habit){
        habitDao.updateHabit(habit)
    }
}
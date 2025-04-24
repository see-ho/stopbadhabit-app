package com.seeho.stopbadhabit.data.model.Habit

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Query("SELECT * FROM habit WHERE state = 0")
    fun getHomeHabits(): List<Habit>

    @Query("SELECT * FROM habit WHERE state = 1 or state = 2")
    fun getHistoryHabits(): List<Habit>

    @Insert
    fun insertHabit(vararg habit: Habit)

    @Query("SELECT * FROM habit WHERE habit_id = :id")
    suspend fun getHabitById(id: Int): Habit?

    @Update
    suspend fun updateHabit(habit: Habit)

    @Query("SELECT * FROM Habit WHERE habit_id = :habitId ")
    fun getHabitByIdFlow(habitId: Int): Flow<Habit?>

}

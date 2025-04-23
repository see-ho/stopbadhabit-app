package com.seeho.stopbadhabit.data.model.HabitAndBattle

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface HabitAndBattleDao {
    @Transaction
    @Query("SELECT * from habit")
    suspend fun getAllHabitAndBattle(): List<HabitAndBattle>?

    @Transaction
    @Query("SELECT * from habit WHERE habit_id = :habit_id")
    suspend fun getHabitAndBattle(habit_id: Int) : HabitAndBattle?
}
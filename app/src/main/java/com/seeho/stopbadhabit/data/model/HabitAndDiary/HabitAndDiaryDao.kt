package com.seeho.stopbadhabit.data.model.HabitAndDiary

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface HabitAndDiaryDao {
    @Transaction
    @Query("SELECT * from habit WHERE state = 1 or state = 2")
    suspend fun getAllHabitAndDiary(): List<HabitAndDiary>?

    @Transaction
    @Query("SELECT * from habit WHERE habit_id = :habit_id")
    suspend fun getHabitAndDiary(habit_id:Int) : HabitAndDiary?

}
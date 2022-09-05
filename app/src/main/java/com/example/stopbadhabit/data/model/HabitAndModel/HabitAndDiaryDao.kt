package com.example.stopbadhabit.data.model.HabitAndModel

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
@Dao
interface HabitAndDiaryDao {
    @Transaction
    @Query("SELECT * from habit")
    suspend fun getAllHabitAndDiary(): List<HabitAndDiary>?

    @Transaction
    @Query("SELECT * from habit WHERE habit_id = :habit_id")
    suspend fun getHabitAndDiary(habit_id:Int) : HabitAndDiary?
}
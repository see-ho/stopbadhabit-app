package com.seeho.roomdbtest.repository

import com.seeho.stopbadhabit.data.model.HabitAndModel.HabitAndDiary
import com.seeho.stopbadhabit.data.model.HabitAndModel.HabitAndDiaryDao

class HabitAndDiaryRepository(private val habitAndDiaryDao: HabitAndDiaryDao) {
    suspend fun getAllHabitAndDiary() : List<HabitAndDiary>? {
        return habitAndDiaryDao.getAllHabitAndDiary()
    }

    suspend fun getHabitAndDiary(habit_id:Int) :HabitAndDiary? {
        return habitAndDiaryDao.getHabitAndDiary(habit_id)
    }

}
package com.seeho.stopbadhabit.data.model.Habit

import androidx.room.*
import com.seeho.stopbadhabit.data.model.Diary.Diary
import com.seeho.stopbadhabit.data.model.Diary.DiaryDao
import com.seeho.stopbadhabit.data.model.HabitAndModel.HabitAndDiaryDao

@Database(entities = [Habit::class,Diary::class], version = 5, exportSchema = false)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun diaryDao(): DiaryDao
    abstract fun habitAndDiaryDao() : HabitAndDiaryDao
}

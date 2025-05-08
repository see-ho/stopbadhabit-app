package com.seeho.stopbadhabit.data.di

import androidx.room.*
import com.seeho.stopbadhabit.data.model.Battle.Battle
import com.seeho.stopbadhabit.data.model.Diary.Diary
import com.seeho.stopbadhabit.data.model.Diary.DiaryDao
import com.seeho.stopbadhabit.data.model.HabitAndDiary.HabitAndDiaryDao
import com.seeho.stopbadhabit.data.model.Battle.BattleDao
import com.seeho.stopbadhabit.data.model.Habit.Habit
import com.seeho.stopbadhabit.data.model.Habit.HabitDao
import com.seeho.stopbadhabit.data.model.HabitAndBattle.HabitAndBattleDao

@Database(entities = [Habit::class,Diary::class, Battle::class], version = 6, exportSchema = true)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun diaryDao(): DiaryDao
    abstract fun habitAndDiaryDao() : HabitAndDiaryDao
    abstract fun battleDao() : BattleDao
    abstract fun habitAndBattleDao() : HabitAndBattleDao
}

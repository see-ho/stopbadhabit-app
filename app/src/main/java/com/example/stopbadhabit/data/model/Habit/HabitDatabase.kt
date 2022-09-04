package com.example.stopbadhabit.data.model.Habit

import android.content.Context
import androidx.room.*
import com.example.stopbadhabit.data.model.Diary.Diary
import com.example.stopbadhabit.data.model.Diary.DiaryDao

@Database(entities = [Habit::class], version = 2, exportSchema = false)
//@TypeConverters(MyTypeConverter::class)
abstract class HabitDatabase : RoomDatabase() {
    //abstract  fun diaryDao() : DiaryDao
    abstract fun habitDao(): HabitDao

    companion object {
        private var INSTANCE: HabitDatabase? = null

        fun getInstance(context: Context): HabitDatabase? {
            if (INSTANCE == null) {
                synchronized(HabitDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        HabitDatabase::class.java, "contact")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }

}

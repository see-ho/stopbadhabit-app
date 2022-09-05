package com.example.stopbadhabit.data.di

import android.content.Context
import androidx.room.Room
import com.example.roomdbtest.repository.DiaryRepository
import com.example.roomdbtest.repository.HabitAndDiaryRepository
import com.example.stopbadhabit.data.model.Diary.DiaryDao
import com.example.stopbadhabit.data.model.Habit.HabitDao
import com.example.stopbadhabit.data.model.Habit.HabitDatabase
import com.example.stopbadhabit.data.model.HabitAndModel.HabitAndDiaryDao
import com.example.stopbadhabit.data.repository.HabitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Di {
    @Singleton
    @Provides
    fun provideHabitDatabase(@ApplicationContext context: Context) : HabitDatabase {
        return Room
            .databaseBuilder(
                context,
                HabitDatabase::class.java,
                "HabitDatabase"
            ).fallbackToDestructiveMigration().build()

    }

    @Singleton
    @Provides
    fun provideHabitDao(habitDB: HabitDatabase): HabitDao {
        return habitDB.habitDao()
    }

    @Singleton
    @Provides
    fun provideHabitRepository(
        habitDao: HabitDao
    ): HabitRepository {
        return HabitRepository(habitDao)
    }

    @Singleton
    @Provides
    fun provideHabitAndDiaryDao(habitDB: HabitDatabase): HabitAndDiaryDao {
        return habitDB.habitAndDiaryDao()
    }

    @Singleton
    @Provides
    fun provideHabitAndDiaryRepository(
        habitAndDiaryDao: HabitAndDiaryDao
    ): HabitAndDiaryRepository {
        return HabitAndDiaryRepository(habitAndDiaryDao)
    }

    @Singleton
    @Provides
    fun provideDiaryDao(habitDB: HabitDatabase): DiaryDao {
        return habitDB.diaryDao()
    }

    @Singleton
    @Provides
    fun providedDiaryRepository(
        diaryDao: DiaryDao
    ): DiaryRepository {
        return DiaryRepository(diaryDao)
    }

}
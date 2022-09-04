package com.example.stopbadhabit.data.di

import android.content.Context
import androidx.room.Room
import com.example.stopbadhabit.data.model.Habit.HabitDao
import com.example.stopbadhabit.data.model.Habit.HabitDatabase
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

}
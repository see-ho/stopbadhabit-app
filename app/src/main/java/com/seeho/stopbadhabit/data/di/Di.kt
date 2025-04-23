package com.seeho.stopbadhabit.data.di

import android.content.Context
import androidx.room.Room
import com.seeho.roomdbtest.repository.DiaryRepository
import com.seeho.roomdbtest.repository.HabitAndDiaryRepository
import com.seeho.stopbadhabit.data.model.Battle.BattleDao
import com.seeho.stopbadhabit.data.model.Diary.DiaryDao
import com.seeho.stopbadhabit.data.model.Habit.HabitDao
import com.seeho.stopbadhabit.data.model.Habit.HabitDatabase
import com.seeho.stopbadhabit.data.model.HabitAndBattle.HabitAndBattleDao
import com.seeho.stopbadhabit.data.model.HabitAndDiary.HabitAndDiaryDao
import com.seeho.stopbadhabit.data.repository.BattleRepository
import com.seeho.stopbadhabit.data.repository.HabitAndBattleRepository
import com.seeho.stopbadhabit.data.repository.HabitRepository
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
                ).fallbackToDestructiveMigration(false).build()

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

    @Singleton
    @Provides
    fun provideHabitAndBattleRepository(
        habitAndBattleDao: HabitAndBattleDao
    ): HabitAndBattleRepository {
        return HabitAndBattleRepository(habitAndBattleDao)
    }

    @Singleton
    @Provides
    fun provideBattleDao(habitDB: HabitDatabase): BattleDao {
        return habitDB.battleDao()
    }

    @Singleton
    @Provides
    fun providedBattleRepository(
        battleDao: BattleDao
    ): BattleRepository {
        return BattleRepository(battleDao)
    }
}
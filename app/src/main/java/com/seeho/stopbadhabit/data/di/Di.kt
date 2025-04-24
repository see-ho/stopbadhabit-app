package com.seeho.stopbadhabit.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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

    val MIGRATION_5_6 = object : Migration(5,6)  {
        override fun migrate(db: SupportSQLiteDatabase) {
            // 1. habit 테이블에 notification_time 컬럼 추가 (default null)
            db.execSQL(
                "ALTER TABLE `Habit` ADD COLUMN `notification_time` TEXT DEFAULT NULL"
            )

            // 2. battle 테이블 생성 (primary key 정확히 지정)
            db.execSQL(
                """
            CREATE TABLE IF NOT EXISTS `battle` (
                `battle_id` INTEGER PRIMARY KEY AUTOINCREMENT,
                `habit_id` INTEGER NOT NULL,
                `battle_date` TEXT NOT NULL,
                `is_success` INTEGER NOT NULL,
                FOREIGN KEY(`habit_id`) REFERENCES `Habit`(`habit_id`) ON UPDATE NO ACTION ON DELETE NO ACTION
            )
            """.trimIndent()
            )

            // 3. 인덱스 생성
            db.execSQL(
                "CREATE INDEX IF NOT EXISTS `index_battle_habit_id` ON `battle`(`habit_id`)"
            )
        }
    }


    @Singleton
    @Provides
    fun provideHabitDatabase(@ApplicationContext context: Context) : HabitDatabase {
        return Room
                .databaseBuilder(
                    context,
                    HabitDatabase::class.java,
                    "HabitDatabase"
                ).addMigrations(MIGRATION_5_6)
                .fallbackToDestructiveMigration(false).build()

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
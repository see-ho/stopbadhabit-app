package com.seeho.stopbadhabit.data.model.Diary

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {
    @Insert
    suspend fun insertDiary(vararg diary: Diary)

    @Query("SELECT * FROM diary WHERE habit_id = (:habit_id)")
    suspend fun getDiaryAll(habit_id : Int): List<Diary>

    @Query("SELECT * FROM diary WHERE diary_id = (:diary_id)")
    suspend fun getDiary(diary_id : Int) : Diary

    @Query("SELECT * FROM diary WHERE habit_id = (:habit_id)")
    fun getFlowDiaryAll(habit_id: Int) : Flow<List<Diary>>
}
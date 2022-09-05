package com.example.stopbadhabit.data.model.Diary

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DiaryDao {
    @Insert
    suspend fun insertDiary(vararg diary: Diary)

    @Query("SELECT * FROM diary WHERE habit_id = (:habit_id)")
    suspend fun getDiaryAll(habit_id : Int): List<Diary>

    @Query("SELECT * FROM diary WHERE diary_id = (:diary_id)")
    suspend fun getDiary(diary_id : Int) : Diary

    @Query("DELETE FROM diary WHERE diary_id = (:diary_id)")
    suspend fun deleteDiary(diary_id: Int)
}
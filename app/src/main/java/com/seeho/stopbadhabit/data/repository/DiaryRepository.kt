package com.seeho.roomdbtest.repository

import com.seeho.stopbadhabit.data.model.Diary.Diary
import com.seeho.stopbadhabit.data.model.Diary.DiaryDao
import kotlinx.coroutines.flow.Flow

class DiaryRepository(private val diaryDao: DiaryDao) {
    suspend fun getDiaryAll(habit_id: Int) : List<Diary> {
        return diaryDao.getDiaryAll(habit_id)
    }

    suspend fun insertDiary(diary: Diary){
        diaryDao.insertDiary(diary)
    }

    suspend fun getDiary(id:Int) : Diary {
        return diaryDao.getDiary(id)
    }

    fun getFlowDiaryAll(habit_id: Int): Flow<List<Diary>> {
        return diaryDao.getFlowDiaryAll(habit_id)
    }
}
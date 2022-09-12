
package com.example.stopbadhabit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdbtest.repository.DiaryRepository
import com.example.roomdbtest.repository.HabitAndDiaryRepository
import com.example.stopbadhabit.data.model.Diary.Diary
import com.example.stopbadhabit.data.model.HabitAndModel.HabitAndDiary
import com.example.stopbadhabit.data.model.PresentHabit.PresentHabit
import com.example.stopbadhabit.data.repository.HabitRepository
import com.example.stopbadhabit.util.ListLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitHistoryViewModel @Inject constructor(
    private val habitRepository: HabitRepository,
    private val habitAndDiaryRepository: HabitAndDiaryRepository,
    private val diaryRepository: DiaryRepository,
): ViewModel()
{
    private val _habitAndDiaryList = MutableLiveData<List<HabitAndDiary>>()
    val habitAndDiaryList : MutableLiveData<List<HabitAndDiary>> get() = _habitAndDiaryList

    init {
        viewModelScope.launch {
            getHabitHistoryList()
        }
    }

    fun getHabitHistoryList() {
        viewModelScope.launch {
            _habitAndDiaryList.postValue(habitAndDiaryRepository.getAllHabitAndDiary())
        }
    }

}

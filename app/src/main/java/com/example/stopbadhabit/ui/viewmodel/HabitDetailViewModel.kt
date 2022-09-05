package com.example.stopbadhabit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdbtest.repository.DiaryRepository
import com.example.stopbadhabit.data.model.Diary.Diary
import com.example.stopbadhabit.data.model.Habit.Habit
import com.example.stopbadhabit.data.repository.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitDetailViewModel @Inject constructor(
    private val habitrepository: HabitRepository,
    private val diaryRepository: DiaryRepository
) :ViewModel() {

    private val _habit = MutableLiveData<Habit>()
    val habit : LiveData<Habit> get() = _habit

    private val _diaryList = MutableLiveData<List<Diary>>()
    val diaryList : LiveData<List<Diary>> get() = _diaryList


    fun getHabitDetail(id: Int){
        viewModelScope.launch {
            _habit.postValue(habitrepository.getHabitById(id))
        }
    }

    fun getDiary(id: Int) {
        viewModelScope.launch {
            _diaryList.postValue(diaryRepository.getDiaryAll(id))
        }
    }
}
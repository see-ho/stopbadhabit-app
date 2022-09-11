package com.example.stopbadhabit.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdbtest.repository.DiaryRepository
import com.example.stopbadhabit.data.model.Diary.Diary
import com.example.stopbadhabit.data.model.Habit.Habit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryDetailViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository
): ViewModel() {
    private val _diary = MutableLiveData<Diary>()
    val diary : LiveData<Diary> get() = _diary

    fun getDiaryDetail(id: Int){
        viewModelScope.launch {
            _diary.postValue(diaryRepository.getDiary(id))
        }
    }
}
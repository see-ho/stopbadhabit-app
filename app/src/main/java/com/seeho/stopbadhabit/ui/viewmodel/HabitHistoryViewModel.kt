
package com.seeho.stopbadhabit.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seeho.roomdbtest.repository.DiaryRepository
import com.seeho.roomdbtest.repository.HabitAndDiaryRepository
import com.seeho.stopbadhabit.data.model.HabitAndModel.HabitAndDiary
import com.seeho.stopbadhabit.data.repository.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

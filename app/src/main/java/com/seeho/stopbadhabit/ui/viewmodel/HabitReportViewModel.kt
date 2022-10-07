package com.seeho.stopbadhabit.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seeho.roomdbtest.repository.HabitAndDiaryRepository
import com.seeho.stopbadhabit.data.model.Habit.Habit
import com.seeho.stopbadhabit.data.model.HabitAndModel.HabitAndDiary
import com.seeho.stopbadhabit.data.repository.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitReportViewModel @Inject constructor(
    private val habitRepository: HabitRepository,
    private val habitAndDiaryRepository: HabitAndDiaryRepository
): ViewModel(){
    private val _habit = MutableLiveData<Habit>()
    val habit : LiveData<Habit> get() = _habit

    private val _habitAndDiary = MutableLiveData<HabitAndDiary>()
    val habitAndDiary: LiveData<HabitAndDiary> get() = _habitAndDiary

    fun getHabitAndDiary(id:Int){
        viewModelScope.launch {
            _habitAndDiary.postValue(habitAndDiaryRepository.getHabitAndDiary(id))
        }
    }

    fun getHabitDetail(id: Int){
        viewModelScope.launch {
            _habit.postValue(habitRepository.getHabitById(id))
        }
    }

    fun updateState(state: Int, endDate: String){
        _habit.value?.let {
            _habit.value=it.copy(state = state, end_date = endDate)
        }
        viewModelScope.launch {
            if(_habit.value != null){
                habitRepository.updateHabit(_habit.value!!)
            }
        }
    }
}
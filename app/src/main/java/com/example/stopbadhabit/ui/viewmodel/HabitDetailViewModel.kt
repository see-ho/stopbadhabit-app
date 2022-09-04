package com.example.stopbadhabit.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stopbadhabit.data.model.Habit.Habit
import com.example.stopbadhabit.data.repository.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitDetailViewModel @Inject constructor(
    private val repository: HabitRepository
) :ViewModel() {

    private val _habit = MutableLiveData<Habit>()
    val habit : LiveData<Habit> get() = _habit

    fun getHabitDetail(id: Int){
        viewModelScope.launch {
            _habit.postValue(repository.getHabitById(id))
        }
    }
}
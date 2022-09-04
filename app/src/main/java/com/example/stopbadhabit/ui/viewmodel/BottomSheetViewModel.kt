package com.example.stopbadhabit.ui.viewmodel

import androidx.lifecycle.*
import com.example.stopbadhabit.data.model.Habit.Habit
import com.example.stopbadhabit.data.repository.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomSheetViewModel @Inject constructor(
    private val repository: HabitRepository
    ) : ViewModel() {

    fun insert(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertHabit(habit)
        }
    }
}
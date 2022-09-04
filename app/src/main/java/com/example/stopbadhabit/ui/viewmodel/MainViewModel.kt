package com.example.stopbadhabit.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stopbadhabit.data.model.Habit.Habit
import com.example.stopbadhabit.data.repository.HabitRepository
import com.example.stopbadhabit.util.ListLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val application: Application,
    private val repository: HabitRepository
):ViewModel(){
    private val _habitList = ListLiveData<Habit>()
    val habitList : LiveData<ArrayList<Habit>> get() = _habitList

    var detailHabitId = -1

    init {
        viewModelScope.launch {
            getHabitList()
        }
    }

    private suspend fun getHabitList() {
        viewModelScope.launch {
            _habitList.clear()
            val list = viewModelScope.async(Dispatchers.IO) {
//            _habitList.addAllAsync(repository.getHomeHabits())
//            Log.e("test","${repository.getHomeHabits()}")
                repository.getHomeHabits()
            }.await()

            _habitList.addAll(list)
        }
    }

    fun insert(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertHabit(habit)
            getHabitList()
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            repository.deleteAll()
            getHabitList()
        }
    }

}

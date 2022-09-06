package com.example.stopbadhabit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stopbadhabit.data.model.Habit.Habit
import com.example.stopbadhabit.data.model.PresentHabit.PresentHabit
import com.example.stopbadhabit.data.repository.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.security.PrivateKey
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HabitRepository,
): ViewModel(){

    private val list = mutableListOf<PresentHabit>()

    private val _habitList = MutableLiveData<List<PresentHabit>>()
    val habitList : LiveData<List<PresentHabit>> get() = _habitList

    private val _habit = MutableLiveData<Habit>()
    val habit : LiveData<Habit> get() = _habit

    private var tmpList:ArrayList<Habit> = ArrayList()



    init {
        viewModelScope.launch(Dispatchers.IO) {
            //val data = repository.getHomeHabits()
            _habitList.postValue(repository.getHomeHabits())
            //addList(data)
        }
    }

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            _habitList.postValue(repository.getHomeHabits())
        }
    }

    fun updateState(habit:Habit) {
        _habit.value?.let {
            _habit.value=it.copy(state = it.state + 1)
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    fun addList(presentHabit: PresentHabit ) {
        list.add(presentHabit)
        _habitList.value = list
    }
}



/* data class HabitListState(
     val data: List<Habit> = emptyList()
 )
 private val _habitListState = MutableStateFlow(HabitListState())
 val habitListState: StateFlow<HabitListState> = _habitListState.asStateFlow()

 var list: List<Habit> = ArrayList()*/

/*private fun getHomeHabits() = viewModelScope.launch{
    repository.getHomeHabits()?.let {
        list = it
    }
     _habitList.value = list

}*/
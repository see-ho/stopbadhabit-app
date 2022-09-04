package com.example.stopbadhabit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stopbadhabit.data.model.Habit.Habit
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

    private val list = mutableListOf<Habit>()

    private val _habitList = MutableLiveData<List<Habit>>()
    val habitList : LiveData<List<Habit>> get() = _habitList

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

    fun deleteAll(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    fun addList(habit:Habit ) {
        list.add(habit)
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
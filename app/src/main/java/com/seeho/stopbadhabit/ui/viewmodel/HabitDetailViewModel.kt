package com.seeho.stopbadhabit.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seeho.roomdbtest.repository.DiaryRepository
import com.seeho.stopbadhabit.data.model.Habit.Habit
import com.seeho.stopbadhabit.data.repository.HabitRepository
import com.seeho.stopbadhabit.util.toDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HabitDetailViewModel @Inject constructor(
    private val habitRepository: HabitRepository,
) :ViewModel() {

    private val _habit = MutableLiveData<Habit>()
    val habit : LiveData<Habit> get() = _habit

    private val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time.time

    fun setFromStartDate(): Long{
        val localHabit = _habit.value
        localHabit?.let {
            val fromStart = (today - it.start_date.toDate()) / (24 * 60 * 60 * 1000)+1
            return fromStart
        }
        return -1
    }

    fun getHabitDetail(id: Int){
        viewModelScope.launch {
            _habit.postValue(habitRepository.getHabitById(id))
        }
    }

}
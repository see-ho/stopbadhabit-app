package com.example.stopbadhabit.ui.viewmodel

import android.app.Application
import android.util.Log
import android.util.LogPrinter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdbtest.repository.DiaryRepository
import com.example.roomdbtest.repository.HabitAndDiaryRepository
import com.example.stopbadhabit.data.model.Diary.Diary
import com.example.stopbadhabit.data.model.Habit.Habit
import com.example.stopbadhabit.data.model.HabitAndModel.HabitAndDiary
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
    private val habitRepository: HabitRepository,
    private val diaryRepository: DiaryRepository,
    private val habitAndDiaryRepository: HabitAndDiaryRepository
):ViewModel(){
    private val _habitList = ListLiveData<Habit>()
    val habitList : LiveData<ArrayList<Habit>> get() = _habitList

    private val _diaryList = ListLiveData<Diary>()
    val diaryList : LiveData<ArrayList<Diary>> get() = _diaryList

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
                habitRepository.getHomeHabits()
            }.await()

            _habitList.addAll(list)
        }
    }

    fun getDiaryList() {
        viewModelScope.launch {
            _diaryList.clear()
            val list = viewModelScope.async(Dispatchers.IO) {
                diaryRepository.getDiaryAll(1)
            }.await()

            _diaryList.addAll(list)
        }
    }

    fun insertHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            habitRepository.insertHabit(habit)
            getHabitList()
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            habitRepository.deleteAll()
            getHabitList()
        }
    }

    fun insertDiary(diary: Diary) {
        viewModelScope.launch(Dispatchers.IO) {
            diaryRepository.insertDiary(diary)
            getDiaryList()
            check()
        }
    }

    suspend fun check() {
        val data: List<Habit> = habitRepository.getHomeHabits()
        Log.e(javaClass.simpleName, "홈 헤비이이이잇:$data", )

        val data2: List<HabitAndDiary>? = habitAndDiaryRepository.getAllHabitAndDiary()
        Log.e(javaClass.simpleName, "헤빗이랑 다이어리!:$data2", )
    }

}

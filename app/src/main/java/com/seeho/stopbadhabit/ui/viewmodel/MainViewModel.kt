package com.seeho.stopbadhabit.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seeho.roomdbtest.repository.DiaryRepository
import com.seeho.stopbadhabit.data.model.Diary.Diary
import com.seeho.stopbadhabit.data.model.Habit.Habit
import com.seeho.stopbadhabit.data.model.PresentHabit.PresentHabit
import com.seeho.stopbadhabit.data.repository.HabitRepository
import com.seeho.stopbadhabit.util.ListLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class  MainViewModel @Inject constructor(
    private val habitRepository: HabitRepository,
    private val diaryRepository: DiaryRepository,
):ViewModel(){
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _habitList = ListLiveData<PresentHabit>()
    val habitList : LiveData<ArrayList<PresentHabit>> get() = _habitList

    private val _diaryList = ListLiveData<Diary>()
    val diaryList : LiveData<ArrayList<Diary>> get() = _diaryList

    private val _detailHabitId =  MutableLiveData(-1)
    val detailHabitId: LiveData<Int> get() = _detailHabitId

    private val _heartlottie =  MutableLiveData(false)
    val heartlottie: LiveData<Boolean> get() = _heartlottie

    init {
        viewModelScope.launch {
            getHabitList()
            delay(1500)
            _isLoading.value=false

        }
    }

    fun playHeartLottie(){
        viewModelScope.launch {
            _heartlottie.value = true
        }
    }

    fun doneHeartLottie(){
        viewModelScope.launch {
            _heartlottie.value = false
        }
    }

    fun setDetailId(id: Int) {
        _detailHabitId.value = id
    }

    fun getHabitList() {
        viewModelScope.launch {
            val list = viewModelScope.async(Dispatchers.IO) {
                habitRepository.getHomeHabits()
            }.await()
            _habitList.clear()
            _habitList.addAll(list)
        }
    }

    fun getDiaryList(id:Int) {
        viewModelScope.launch {
            _diaryList.clear()
            val list = viewModelScope.async(Dispatchers.IO) {
                diaryRepository.getDiaryAll(id)
            }.await()
            _diaryList.clear()
            _diaryList.addAll(list)
        }
    }

    fun insertHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            habitRepository.insertHabit(habit)
            getHabitList()
        }
    }

    fun insertDiary(diary: Diary, id: Int, habit: Habit?) {
        habit ?:return
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                diaryRepository.insertDiary(diary)
            }.join()
            habitRepository.updateHabit(habit)
            getHabitList()
        }
    }

}

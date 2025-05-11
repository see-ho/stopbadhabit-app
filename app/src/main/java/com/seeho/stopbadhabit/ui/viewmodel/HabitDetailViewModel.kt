package com.seeho.stopbadhabit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seeho.roomdbtest.repository.DiaryRepository
import com.seeho.stopbadhabit.data.model.Battle.Battle
import com.seeho.stopbadhabit.data.model.Habit.Habit
import com.seeho.stopbadhabit.data.model.HabitAndBattle.HabitAndBattle
import com.seeho.stopbadhabit.data.model.PresentHabit.DayStatus
import com.seeho.stopbadhabit.data.model.PresentHabit.PresentBattle
import com.seeho.stopbadhabit.data.model.PresentHabit.PresentHabit
import com.seeho.stopbadhabit.data.repository.BattleRepository
import com.seeho.stopbadhabit.data.repository.HabitAndBattleRepository
import com.seeho.stopbadhabit.data.repository.HabitRepository
import com.seeho.stopbadhabit.util.toDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HabitDetailViewModel @Inject constructor(
    private val habitRepository: HabitRepository,
    private val battleRepository: BattleRepository,
    private val habitAndBattleRepository: HabitAndBattleRepository,
) : ViewModel() {

    private val _habitFlow = MutableStateFlow<Habit?>(null)
    val habitFlow: StateFlow<Habit?> get() = _habitFlow

    private val _habit = MutableLiveData<Habit>()
    val habit: LiveData<Habit> get() = _habit

    val isFailDialogVisible = MutableStateFlow(false)
    val openDiaryBottomSheet = MutableStateFlow(false)

    init {
        Log.e("TAG", "init: I', new! ", )
    }

    fun onShieldClick() {
        isFailDialogVisible.value = true
        openDiaryBottomSheet.value = false
    }

    fun onDialogDismiss(){
        isFailDialogVisible.value = false
    }

    fun confirmFail() {
        isFailDialogVisible.value = false
        openDiaryBottomSheet.value = true
        Log.e("TAG", "confirmFail: ${openDiaryBottomSheet.value}", )
    }

    fun bottomSheetClose(){
        openDiaryBottomSheet.value = false
        Log.e("TAG", "confirmFail: ${openDiaryBottomSheet.value}", )
    }

    fun habitById(habitId: Int): StateFlow<Habit?> {
        return habitRepository.getHabitByIdFlow(habitId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
    }


    fun presentBattlesFlow(habitId: Int): StateFlow<List<PresentBattle>> {
        return habitAndBattleRepository.getPresentBattles(habitId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    }

    private val _isLottieVisible = MutableStateFlow(false)
    val isLottieVisible: StateFlow<Boolean> = _isLottieVisible

    fun triggerLottie() {
        _isLottieVisible.value = true
        viewModelScope.launch {
            delay(2000) // 애니메이션 시간
            _isLottieVisible.value = false
        }
    }

    fun insertBattle(habitId: Int, battle: PresentBattle) {
        viewModelScope.launch {
            battleRepository.insertBattle(
                Battle(
                    habit_id = habitId,
                    battle_date = battle.battle_date,
                    is_success = when(battle.status){
                        DayStatus.SUCCESS -> true
                        DayStatus.FAIL -> false
                        else -> false
                    }
                )
            )
        }
    }

    private val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time.time

    fun setFromStartDate(): Long {
        val localHabit = _habit.value
        localHabit?.let {
            val fromStart = (today - it.start_date.toDate()) / (24 * 60 * 60 * 1000) + 1
            return fromStart
        }
        return -1
    }

    fun getHabitDetail(id: Int) {
        viewModelScope.launch {
            _habit.postValue(habitRepository.getHabitById(id))
        }
    }

}
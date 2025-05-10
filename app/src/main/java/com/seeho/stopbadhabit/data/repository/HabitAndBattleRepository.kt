package com.seeho.stopbadhabit.data.repository

import com.seeho.stopbadhabit.data.model.HabitAndBattle.HabitAndBattle
import com.seeho.stopbadhabit.data.model.HabitAndBattle.HabitAndBattleDao
import com.seeho.stopbadhabit.data.model.PresentHabit.DayStatus
import com.seeho.stopbadhabit.data.model.PresentHabit.PresentBattle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class HabitAndBattleRepository(private val habitAndBattleDao: HabitAndBattleDao) {
    suspend fun getAllHabitAndBattle(): List<HabitAndBattle>? {
        return habitAndBattleDao.getAllHabitAndBattle()
    }


    fun getPresentBattles(habit_id: Int): Flow<List<PresentBattle>> {
        return habitAndBattleDao.getHabitAndBattle(habit_id)
            .map { habitAndBattle ->
                if (habitAndBattle != null) {
                    val habit = habitAndBattle.habit
                    val goal = habit.goal_date
                    val startDate = LocalDate.parse(habit.start_date)
                    val battles = habitAndBattle.battles.orEmpty()

                    val existingMap = battles.associateBy { it.battle_date }

                    (0 until goal).map { dayOffset ->
                        val date = startDate.plusDays(dayOffset.toLong()).toString()
                        val existing = existingMap[date]
                        if (existing != null) {
                            PresentBattle(
                                battle_id = existing.battle_id,
                                habit_id = existing.habit_id,
                                battle_date = existing.battle_date,
                                status = if (existing.is_success) DayStatus.SUCCESS else DayStatus.FAIL
                            )
                        } else {
                            PresentBattle(
                                habit_id = habit.habit_id ?: -1,
                                battle_date = date,
                                status = DayStatus.NONE
                            )
                        }
                    }
                } else {
                    emptyList()
                }
            }.flowOn(Dispatchers.IO)
    }
}
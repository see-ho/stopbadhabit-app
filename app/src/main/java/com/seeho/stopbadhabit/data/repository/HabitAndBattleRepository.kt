package com.seeho.stopbadhabit.data.repository

import com.seeho.stopbadhabit.data.model.HabitAndBattle.HabitAndBattle
import com.seeho.stopbadhabit.data.model.HabitAndBattle.HabitAndBattleDao

class HabitAndBattleRepository(private val habitAndBattleDao: HabitAndBattleDao) {
    suspend fun getAllHabitAndBattle(): List<HabitAndBattle>? {
        return habitAndBattleDao.getAllHabitAndBattle()
    }

    suspend fun getHabitAndBattle(habit_id: Int): HabitAndBattle? {
        return habitAndBattleDao.getHabitAndBattle(habit_id)
    }
}
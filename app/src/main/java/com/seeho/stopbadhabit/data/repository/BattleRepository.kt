package com.seeho.stopbadhabit.data.repository

import com.seeho.stopbadhabit.data.model.Battle.Battle
import com.seeho.stopbadhabit.data.model.Battle.BattleDao

class BattleRepository(private val battleDao: BattleDao) {
    suspend fun getBattleAll(habit_id: Int) : List<Battle> {
        return battleDao.getBattleAll(habit_id)
    }

    suspend fun insertBattle(battle: Battle){
        battleDao.insertBattle(battle)
    }
}
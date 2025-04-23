package com.seeho.stopbadhabit.data.model.Battle

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BattleDao {
    @Insert
    suspend fun insertBattle(vararg battle: Battle)

    @Query("SELECT * FROM battle WHERE habit_id = (:habit_id)")
    suspend fun getBattleAll(habit_id : Int): List<Battle>

}
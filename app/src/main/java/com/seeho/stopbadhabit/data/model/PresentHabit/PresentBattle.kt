package com.seeho.stopbadhabit.data.model.PresentHabit

enum class DayStatus{
    SUCCESS, FAIL, NONE
}

data class PresentBattle(
    val battle_id: Int? = null,

    val habit_id : Int,

    val battle_date: String,

    val status: DayStatus,

)

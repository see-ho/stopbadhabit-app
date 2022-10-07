package com.seeho.stopbadhabit.util

import com.seeho.stopbadhabit.data.model.Habit.Habit
import com.seeho.stopbadhabit.data.model.PresentHabit.PresentHabit
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun String.toDate(): Long {
    val list = this.split("-")
    if (list.size != 3) throw Throwable("3아님")

    val startDate = Calendar.getInstance().apply {
        set(list[0].toInt(),list[1].toInt()-1,list[2].toInt())
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    return startDate.time.time
}

fun String.toCalender(goalDate:Int) : String {
    val list = this.split("-")
    if (list.size != 3) throw Throwable("3아님")

    val endDate = Calendar.getInstance().apply {
        set(list[0].toInt(),list[1].toInt()-1,list[2].toInt())
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    endDate.add(
        Calendar.DATE, goalDate
    )
    val formatter = DateTimeFormatter.ISO_DATE

    val endDateTime = LocalDate.of(endDate.get(Calendar.YEAR),endDate.get(Calendar.MONTH)+1,endDate.get(Calendar.DAY_OF_MONTH))

    return endDateTime.format(formatter)
}

fun Habit.toPresentHabit() : PresentHabit {
    val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time.time

    val dateFromStart = ((today - start_date.toDate()) / (24 * 60 * 60 * 1000) + 1).toInt()

    return if(current_life<=0 || dateFromStart >= goal_date+1) PresentHabit(habit = this,1)
    else PresentHabit(habit = this,0)
}


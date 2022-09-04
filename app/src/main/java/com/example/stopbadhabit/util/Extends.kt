package com.example.stopbadhabit.util

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
    //startDate.set(list[0].toInt(),list[1].toInt(),list[2].toInt())

    return startDate.time.time
}

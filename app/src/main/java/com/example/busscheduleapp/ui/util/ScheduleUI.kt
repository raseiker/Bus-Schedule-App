package com.example.busscheduleapp.ui.util

import com.example.busscheduleapp.data.data_source.entity.BusEntity

data class ScheduleUI (
    val details: ScheduleDetailUI = ScheduleDetailUI(),
    val isEntryValid: Boolean = false
)

data class ScheduleDetailUI(
    val stopName: String = "",
    val arrivalTime: String = "",
    val idBus: Int = 0
)

fun ScheduleDetailUI.toScheduleEntity() = BusEntity (
    idBus = idBus,
    arrivalTime = arrivalTime.toInt(10),
    stopName = stopName
)

fun ScheduleDetailUI.isValid() = stopName.isNotBlank() && arrivalTime.isNotBlank()

fun ScheduleDetailUI.isDifferent(scheduleDetailUI: ScheduleDetailUI) = this != scheduleDetailUI
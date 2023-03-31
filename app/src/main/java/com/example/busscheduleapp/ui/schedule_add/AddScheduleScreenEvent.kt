package com.example.busscheduleapp.ui.schedule_add

import com.example.busscheduleapp.ui.util.ScheduleDetailUI

sealed interface AddScheduleScreenEvent{
    data class AddNewSchedule(val scheduleUI: ScheduleDetailUI): AddScheduleScreenEvent
    data class OnValueChange(val scheduleUI: ScheduleDetailUI) : AddScheduleScreenEvent
}
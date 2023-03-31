package com.example.busscheduleapp.ui.schedule_details

import com.example.busscheduleapp.ui.util.ScheduleDetailUI

sealed interface ScheduleDetailsEvent {
    data class OnValueChange(val scheduleUI: ScheduleDetailUI): ScheduleDetailsEvent
    data class UpdateSchedule(val scheduleUI: ScheduleDetailUI): ScheduleDetailsEvent
}
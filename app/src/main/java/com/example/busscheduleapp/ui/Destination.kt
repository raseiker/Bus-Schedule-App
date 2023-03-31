package com.example.busscheduleapp.ui

sealed class Destination(
    val route: String = "schedules",
) {
    object ScheduleListScreen: Destination()
    object ScheduleScreen: Destination(route = "schedules/"){
        const val arg: String = "scheduleId"
        val routeWithArg : String = "$route{$arg}"
    }
    object AddScheduleScreen: Destination(route = "schedule/add")
}

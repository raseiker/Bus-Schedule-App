package com.example.busscheduleapp.ui.bus_list

import com.example.busscheduleapp.data.data_source.entity.BusEntity

sealed interface BusListEvent {
    data class OnClickListItem(val schedule: BusEntity): BusListEvent
    data class OnAddClicked(val route: String): BusListEvent
    data class OnDeleteClicked(val schedule: BusEntity): BusListEvent
}

package com.example.busscheduleapp.ui.schedule_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.busscheduleapp.data.Response
import com.example.busscheduleapp.data.data_source.entity.BusEntity
import com.example.busscheduleapp.data.repository.BusRepository
import com.example.busscheduleapp.ui.Destination
import com.example.busscheduleapp.ui.util.ScheduleDetailUI
import com.example.busscheduleapp.ui.util.ScheduleUI
import com.example.busscheduleapp.ui.util.isDifferent
import com.example.busscheduleapp.ui.util.toScheduleEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleDetailsViewModel @Inject constructor(
    private val busRepository: BusRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var scheduleState: Response<StateFlow<BusEntity>> = Response.Loading
        private set
    var scheduleUI by mutableStateOf(ScheduleUI())

    init {
//        scheduleState = Response.Loading
//        scheduleState = try {
//            Response.Success(busRepository.getBusById(savedStateHandle[Destination.ScheduleScreen.arg]!!)
//                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), BusEntity())
//            )
//        } catch (e: Exception) {
//            Response.Error(e.message)
//        }
        viewModelScope.launch {
            busRepository.getBusById(savedStateHandle[Destination.ScheduleScreen.arg]!!)
                .onStart {
                    scheduleState = Response.Loading
                }
                .collect { schedule ->
                scheduleUI = ScheduleUI (
                    details = ScheduleDetailUI (
                        idBus = schedule.idBus,
                        arrivalTime = schedule.arrivalTime.toString(),
                        stopName = schedule.stopName
                    )
                )
            }
        }
    }

    fun onEvent(event: ScheduleDetailsEvent) {
        when (event) {
            is ScheduleDetailsEvent.OnValueChange -> {
                scheduleUI = ScheduleUI(event.scheduleUI, scheduleUI.details.isDifferent(event.scheduleUI))
            }
            is ScheduleDetailsEvent.UpdateSchedule -> {
                viewModelScope.launch {
                    busRepository.updateBus(event.scheduleUI.toScheduleEntity())
                }
            }
        }
    }
}
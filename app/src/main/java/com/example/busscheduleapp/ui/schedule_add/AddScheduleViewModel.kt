package com.example.busscheduleapp.ui.schedule_add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.busscheduleapp.data.repository.BusRepository
import com.example.busscheduleapp.ui.util.ScheduleUI
import com.example.busscheduleapp.ui.util.isValid
import com.example.busscheduleapp.ui.util.toScheduleEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddScheduleViewModel @Inject constructor(
    private val busRepository: BusRepository
): ViewModel() {

    var scheduleUI by mutableStateOf(ScheduleUI())
        private  set

    fun onEvent(event: AddScheduleScreenEvent) {
        when (event) {
            is AddScheduleScreenEvent.AddNewSchedule -> {
                viewModelScope.launch {
                    busRepository.insertBus(event.scheduleUI.toScheduleEntity())
                }
            }
            is AddScheduleScreenEvent.OnValueChange -> {
                scheduleUI = ScheduleUI(event.scheduleUI, event.scheduleUI.isValid())
            }
        }
    }
}
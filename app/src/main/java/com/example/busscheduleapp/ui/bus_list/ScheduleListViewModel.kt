package com.example.busscheduleapp.ui.bus_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.busscheduleapp.data.Response
import com.example.busscheduleapp.data.data_source.entity.BusEntity
import com.example.busscheduleapp.data.repository.BusRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for [ScheduleListScreen] composable.
 */
@HiltViewModel
class ScheduleListViewModel @Inject constructor(
    private val busRepository: BusRepository
) : ViewModel() {
    var scheduleListState :  Response<StateFlow<List<BusEntity>>> by mutableStateOf(Response.Loading)
        private set
    var scheduleState: Response<StateFlow<BusEntity>> = Response.Loading
        private set

    init {
        getAllSchedule()
    }

    /**
     * Function for obtain all schedule from database.
     * Set [scheduleListState] accordingly
     */
    private fun getAllSchedule() {
//        scheduleListState = Response.Loading
//        viewModelScope.launch {
//            scheduleListState = try {
//                Response.Success(busRepository.getAllBus().stateIn(this))//, SharingStarted.WhileSubscribed(5000L), emptyList()))
//            } catch (e: Exception) {
//                Response.Error(e.message)
//            }
//        }
        viewModelScope.launch {
            scheduleListState = Response.Success(busRepository.getAllBus()
                .catch { scheduleListState = Response.Error(it.message) }
                .onStart { scheduleListState = Response.Loading }.also { delay(1500L) }
                .stateIn(viewModelScope)//convert from cold flow to hot stateflow
            )
        }
    }

    /**
     * Function for every user interaction.
     * Set [scheduleState] accordingly.
     * @param event is the sealed interface attached to [ScheduleListViewModel].
     */
    fun onEvent(event: BusListEvent){
        when (event){
            is BusListEvent.OnAddClicked -> Unit
            is BusListEvent.OnClickListItem -> {
                scheduleState = Response.Loading
                scheduleState = try {
                    Response.Success(busRepository.getBusById(event.schedule.idBus)
                        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), BusEntity())
                    )
                } catch (e: Exception) {
                    Response.Error(e.message)
                }
            }
            is BusListEvent.OnDeleteClicked -> {
                viewModelScope.launch {
                    busRepository.deleteBus(event.schedule)
                }
            }
        }
    }
}
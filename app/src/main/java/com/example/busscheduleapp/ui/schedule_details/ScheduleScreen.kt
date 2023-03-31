package com.example.busscheduleapp.ui.schedule_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.busscheduleapp.ui.util.ScheduleUI

@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    viewModel: ScheduleDetailsViewModel = hiltViewModel()
) {
//    when(val res = viewModel.scheduleState) {
//        is Response.Error -> Unit
//        Response.Loading -> ShowLoading()
//        is Response.Success -> ShowScheduleContent(
//            busEntity = res.data.collectAsState().value,
//            onEvent = viewModel::onEvent
//        )
//    }
    ShowScheduleContent(
        scheduleUI = viewModel.scheduleUI,
        onEvent = viewModel::onEvent,
        navigateUp = navigateUp,
        modifier = modifier
    )
}

@Composable
fun ShowScheduleContent(
    modifier: Modifier = Modifier,
    scheduleUI: ScheduleUI,
    onEvent: (ScheduleDetailsEvent) -> Unit,
    navigateUp: () -> Unit,
    ) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = scheduleUI.details.stopName,
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = scheduleUI.details.idBus.toString(),
            style = MaterialTheme.typography.h6.copy(color = Color.DarkGray),
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = scheduleUI.details.stopName,
            onValueChange = { onEvent(ScheduleDetailsEvent.OnValueChange(scheduleUI.details.copy(stopName = it))) },
            label = { Text(text = "Stop name") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = scheduleUI.details.arrivalTime,
            onValueChange = { onEvent(ScheduleDetailsEvent.OnValueChange(scheduleUI.details.copy(arrivalTime = it))) },
            label = { Text(text = "Arrival time") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                onEvent(ScheduleDetailsEvent.UpdateSchedule(scheduleUI.details))
                navigateUp()
                      },
            enabled = scheduleUI.isEntryValid,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Update item")
        }
    }
}

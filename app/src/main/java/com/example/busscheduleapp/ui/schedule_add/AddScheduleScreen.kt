package com.example.busscheduleapp.ui.schedule_add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.busscheduleapp.ui.util.ScheduleUI

@Composable
fun AddScheduleScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    viewModel: AddScheduleViewModel = hiltViewModel()
) {
    ShowScheduleAddContent(
        scheduleUI = viewModel.scheduleUI,
        onEvent = viewModel::onEvent,
        modifier = modifier,
        navigateUp = navigateUp
    )
}

@Composable
fun ShowScheduleAddContent(
    modifier: Modifier = Modifier,
    scheduleUI: ScheduleUI,
    onEvent: (AddScheduleScreenEvent) -> Unit,
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
            onValueChange = { onEvent(AddScheduleScreenEvent.OnValueChange(scheduleUI.details.copy(stopName = it))) },
            label = { Text(text = "Stop name") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = scheduleUI.details.arrivalTime,
            onValueChange = { onEvent(AddScheduleScreenEvent.OnValueChange(scheduleUI.details.copy(arrivalTime = it))) },
            label = { Text(text = "Arrival time") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                onEvent(AddScheduleScreenEvent.AddNewSchedule(scheduleUI.details))
                navigateUp()
                      },
            enabled = scheduleUI.isEntryValid,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Add item")
        }
    }
}
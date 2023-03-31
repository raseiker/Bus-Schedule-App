package com.example.busscheduleapp.ui.bus_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.busscheduleapp.data.data_source.entity.BusEntity

@Composable
fun ScheduleItem(
    modifier: Modifier = Modifier,
    schedule: BusEntity?,
    onEvent: (BusListEvent) -> Unit,
    navigateToScheduleItem: (scheduleId: Int) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
//            onEvent(BusListEvent.onClickListItem(schedule!!))
                navigateToScheduleItem(schedule!!.idBus)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = schedule?.stopName ?: "Stop Name",
            style = MaterialTheme.typography.subtitle1.let { if (schedule == null) it.copy(fontWeight = FontWeight.SemiBold) else it },
            modifier = Modifier.weight(1f),
            maxLines = 2
        )
        Text(
            text = schedule?.arrivalTime?.toString() ?: "Arrival Time",
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.SemiBold),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )
        IconButton(
            onClick = { onEvent(BusListEvent.OnDeleteClicked(schedule!!)) },
        ){
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "delete item",
            )
        }
    }
}

@Preview
@Composable
fun PrevScheduleItem() {
    ScheduleItem(schedule = BusEntity(stopName = "Cusco rico", arrivalTime = 123456), onEvent =  {}, navigateToScheduleItem = {} )
}
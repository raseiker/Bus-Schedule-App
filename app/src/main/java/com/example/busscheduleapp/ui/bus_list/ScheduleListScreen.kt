package com.example.busscheduleapp.ui.bus_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.busscheduleapp.data.Response
import com.example.busscheduleapp.data.data_source.entity.BusEntity

/**
 * Top level composable
 */
@Composable
fun ScheduleListScreen(
    modifier : Modifier = Modifier,
    viewModel: ScheduleListViewModel = hiltViewModel(),
    navigateToScheduleItem: (scheduleId: Int) -> Unit
) {
    when(val res = viewModel.scheduleListState) {
        is Response.Error -> Unit
        Response.Loading -> ShowLoading()
        is Response.Success -> {
            val listState by res.data.collectAsState()
            ShowContent(
                modifier = modifier,
                list = listState,
                onEvent = viewModel::onEvent,
                navigateToScheduleItem = navigateToScheduleItem
            )
        }
    }
}

@Composable
fun ShowLoading() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}

@Composable
fun ShowContent(
    modifier: Modifier,
    list: List<BusEntity>,
    onEvent: (BusListEvent) -> Unit,
    navigateToScheduleItem: (scheduleId: Int) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 30.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            ScheduleItem(schedule = null, onEvent = {}, navigateToScheduleItem = {})
        }
        items(list, key = { it.idBus }) { schedule ->
            ScheduleItem(
                schedule = schedule,
                onEvent = onEvent,
                navigateToScheduleItem = navigateToScheduleItem
            )
        }
    }
}

@Preview
@Composable
fun ScheduleScreenPrev() {
//    ScheduleScreen(list = listOf(BusEntity(stopName = "Oquendo city", arrivalTime = 123456),BusEntity(stopName = "Luriwanchinton", arrivalTime = 789456) ))
}
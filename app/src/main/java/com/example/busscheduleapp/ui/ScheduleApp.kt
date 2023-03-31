package com.example.busscheduleapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.busscheduleapp.ui.schedule_details.ScheduleScreen
import com.example.busscheduleapp.ui.bus_list.ScheduleListScreen
import com.example.busscheduleapp.ui.schedule_add.AddScheduleScreen

/**
 * NavHost for all top level screens
 */
@Composable
fun ScheduleApp(
    modifier : Modifier = Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    val backStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination?.route ?: Destination.ScheduleListScreen.route
    Scaffold(
        floatingActionButton = {
            if (currentDestination == Destination.ScheduleListScreen.route)
                FloatingActionButton(onClick = {
                    navHostController.navigate(Destination.AddScheduleScreen.route)
                }) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
                }
        }
    ) {
        NavHost(
            modifier = modifier.padding(it),
            navController = navHostController,
            startDestination = Destination.ScheduleListScreen.route
        ){
            composable(Destination.ScheduleListScreen.route){
                ScheduleListScreen(
                    navigateToScheduleItem = {
                        scheduleId -> navHostController.navigate(Destination.ScheduleScreen.route + scheduleId)
                    },
                    modifier = Modifier.padding(vertical = 15.dp, horizontal = 20.dp)
                )
            }

            composable(
                route = Destination.ScheduleScreen.routeWithArg,
                arguments = listOf (
                    navArgument(Destination.ScheduleScreen.arg) {
                        type = NavType.IntType
                    }
                )
            ){
                ScheduleScreen(
                    navigateUp = { navHostController.navigateUp() },
                    modifier = Modifier.padding(vertical = 15.dp, horizontal = 20.dp)
                )
            }

            composable(Destination.AddScheduleScreen.route) {
                AddScheduleScreen(
                    navigateUp = { navHostController.navigateUp() },
                    modifier = Modifier.padding(vertical = 15.dp, horizontal = 20.dp)
                )
            }
        }
    }
}
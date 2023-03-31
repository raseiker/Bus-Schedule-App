package com.example.busscheduleapp.data.data_source.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "Schedule")
data class BusEntity (
    @ColumnInfo(name = "stop_name") val stopName: String = "",
    @ColumnInfo(name = "arrival_time") val arrivalTime: Int = 0,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val idBus: Int = 0
)
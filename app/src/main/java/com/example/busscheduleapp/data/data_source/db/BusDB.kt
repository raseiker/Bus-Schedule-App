package com.example.busscheduleapp.data.data_source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.busscheduleapp.data.data_source.dao.BusDAO
import com.example.busscheduleapp.data.data_source.entity.BusEntity

@Database(entities = [BusEntity::class], version = 3, exportSchema = false)
abstract class BusDB: RoomDatabase() {
    abstract val busDao : BusDAO
}
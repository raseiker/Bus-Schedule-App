package com.example.busscheduleapp.data.repository

import com.example.busscheduleapp.data.data_source.entity.BusEntity
import kotlinx.coroutines.flow.Flow

interface BusRepository {
    fun getAllBus(): Flow<List<BusEntity>>

    fun getBusById(idBus: Int): Flow<BusEntity>

    suspend fun insertBus(busEntity: BusEntity)

    suspend fun updateBus(busEntity: BusEntity)

    suspend fun deleteBus(busEntity: BusEntity)
}
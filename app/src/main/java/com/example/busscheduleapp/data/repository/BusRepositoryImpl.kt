package com.example.busscheduleapp.data.repository

import com.example.busscheduleapp.data.data_source.dao.BusDAO
import com.example.busscheduleapp.data.data_source.entity.BusEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BusRepositoryImpl @Inject constructor(
    private val busDAO: BusDAO
): BusRepository {
    override fun getAllBus(): Flow<List<BusEntity>> {
        return busDAO.getAllBus()
    }

    override fun getBusById(idBus: Int): Flow<BusEntity> {
        return busDAO.getBusById(idBus)
    }

    override suspend fun insertBus(busEntity: BusEntity) {
        busDAO.insertBus(busEntity)
    }

    override suspend fun updateBus(busEntity: BusEntity) {
        busDAO.updateBus(busEntity)
    }

    override suspend fun deleteBus(busEntity: BusEntity) {
        busDAO.deleteBus(busEntity)
    }
}
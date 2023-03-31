package com.example.busscheduleapp.data.data_source.dao

import androidx.room.*
import com.example.busscheduleapp.data.data_source.entity.BusEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BusDAO {
    @Query("SELECT * FROM schedule ORDER BY arrival_time DESC")
    fun getAllBus(): Flow<List<BusEntity>>

    @Query("SELECT * FROM schedule WHERE id = :idBus")
    fun getBusById(idBus: Int): Flow<BusEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBus(busEntity: BusEntity)

    @Update()
    suspend fun updateBus(busEntity: BusEntity)

    @Delete
    suspend fun deleteBus(busEntity: BusEntity)
}
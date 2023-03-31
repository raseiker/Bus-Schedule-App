package com.example.busscheduleapp.di

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import com.example.busscheduleapp.data.data_source.dao.BusDAO
import com.example.busscheduleapp.data.data_source.db.BusDB
import com.example.busscheduleapp.data.repository.BusRepository
import com.example.busscheduleapp.data.repository.BusRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideBusDB(
        application: Application
    ): BusDB {
        return Room.databaseBuilder(
            context = application,
            klass = BusDB::class.java,
            name = "BusDatabase"
        )
//            .createFromAsset(databaseFilePath = "db/bus_schedule.db")
            .fallbackToDestructiveMigration()//use it when schemas change
            .build()

//            .busDao
    }

//    @Provides
//    @Singleton
//    fun provideBusDao(busDB: BusDB): BusDAO {
//        return busDB.busDao()
//    }

    @Provides
    @Singleton
    fun provideBusRepository(busDB: BusDB): BusRepository {
        return BusRepositoryImpl(busDB.busDao)
    }
}
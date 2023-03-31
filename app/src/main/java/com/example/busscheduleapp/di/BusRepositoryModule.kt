package com.example.busscheduleapp.di

import com.example.busscheduleapp.data.repository.BusRepository
import com.example.busscheduleapp.data.repository.BusRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BusRepositoryModule {
//
//    @Binds
//    @Singleton
//    abstract fun bindBusRepository(
//        busRepositoryImpl: BusRepositoryImpl
//    ): BusRepository
}
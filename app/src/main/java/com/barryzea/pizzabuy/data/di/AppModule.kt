package com.barryzea.pizzabuy.data.di

import android.app.Application
import androidx.room.Room
import com.barryzea.pizzabuy.data.dataSource.RoomDatasourceImpl
import com.barryzea.pizzabuy.data.dataSource.RoomDatasourceInterface
import com.barryzea.pizzabuy.data.database.PizzaDatabase
import com.barryzea.pizzabuy.data.model.Pizza
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/****
 * Project PizzaBuy
 * Created by Barry Zea H. on 24/11/2022.
 * Copyright (c)  All rights reserved.
 ***/
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun dataBaseProvider(app:Application)= Room.databaseBuilder(
        app,
        PizzaDatabase::class.java,
        "pizza_db"
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    fun roomDataSourceProvider(db:PizzaDatabase):RoomDatasourceInterface=RoomDatasourceImpl(db)
}
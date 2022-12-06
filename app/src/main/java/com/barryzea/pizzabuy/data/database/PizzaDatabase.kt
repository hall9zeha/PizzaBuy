package com.barryzea.pizzabuy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.barryzea.pizzabuy.data.model.Pizza
import com.barryzea.pizzabuy.data.model.PizzaDetail
import com.barryzea.pizzabuy.data.model.Topic

/****
 * Project PizzaBuy
 * Created by Barry Zea H. on 24/11/2022.
 * Copyright (c)  All rights reserved.
 ***/
@Database(entities = [Pizza::class, PizzaDetail::class, Topic::class], version= 4)
abstract class PizzaDatabase:RoomDatabase() {
    abstract fun pizzaDAO():MyDAO
}
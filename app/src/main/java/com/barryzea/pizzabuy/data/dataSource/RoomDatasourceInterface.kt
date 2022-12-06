package com.barryzea.pizzabuy.data.dataSource

import androidx.lifecycle.MutableLiveData
import com.barryzea.pizzabuy.data.model.Pizza
import com.barryzea.pizzabuy.data.model.PizzaAndTopics
import com.barryzea.pizzabuy.data.model.PizzaDetail
import com.barryzea.pizzabuy.data.model.Topic

/****
 * Project PizzaBuy
 * Created by Barry Zea H. on 24/11/2022.
 * Copyright (c)  All rights reserved.
 ***/
interface RoomDatasourceInterface {
    suspend fun getPizzaAndTopics():List<PizzaAndTopics>
    suspend fun getPizzaById(id:Long):PizzaAndTopics
    suspend fun savePizza(pizza:Pizza):Long
    suspend fun saveTopic(topic:Topic)
    suspend fun deletePizzaOfCar(pizza:Pizza)
    suspend fun saveDetail(detail:List<PizzaDetail>):List<Long>
    suspend fun updateTopics(topics:List<Topic>):Int
    suspend fun clearCarList()
}
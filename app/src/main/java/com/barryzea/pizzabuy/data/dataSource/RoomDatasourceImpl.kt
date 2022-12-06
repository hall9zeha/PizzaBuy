package com.barryzea.pizzabuy.data.dataSource

import androidx.lifecycle.MutableLiveData
import com.barryzea.pizzabuy.data.database.PizzaDatabase
import com.barryzea.pizzabuy.data.model.Pizza
import com.barryzea.pizzabuy.data.model.PizzaAndTopics
import com.barryzea.pizzabuy.data.model.PizzaDetail
import com.barryzea.pizzabuy.data.model.Topic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/****
 * Project PizzaBuy
 * Created by Barry Zea H. on 24/11/2022.
 * Copyright (c)  All rights reserved.
 ***/

class RoomDatasourceImpl(db:PizzaDatabase):RoomDatasourceInterface {
    private val database=db.pizzaDAO()

    override suspend fun getPizzaAndTopics(): List<PizzaAndTopics> = withContext(Dispatchers.IO) {
        return@withContext database.getPizzaAndTopics()
    }

    override suspend fun getPizzaById(id: Long): PizzaAndTopics = withContext(Dispatchers.IO){
        return@withContext database.getPizzaById(id)
    }

    override suspend fun savePizza(pizza: Pizza):Long = withContext(Dispatchers.IO){

        return@withContext database.savePizzaInCar(pizza)
    }

    override suspend fun clearCarList()= withContext(Dispatchers.IO) {
        database.deleteAllPizzas()
    }

    override suspend fun saveTopic(topic: Topic) = withContext(Dispatchers.IO){
        database.saveTopics(topic)
    }

    override suspend fun saveDetail(detail: List<PizzaDetail>):List<Long> = withContext(Dispatchers.IO) {
        return@withContext database.saveDetail(detail)
    }

    override suspend fun updateTopics(topics:List<Topic>): Int = withContext(Dispatchers.IO){
        return@withContext database.updateTopics(topics)
    }

    override suspend fun deletePizzaOfCar(pizza: Pizza) = withContext(Dispatchers.IO) {
        database.deleteItemOfCar(pizza)
    }
}
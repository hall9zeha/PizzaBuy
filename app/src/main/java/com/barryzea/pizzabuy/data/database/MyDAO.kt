package com.barryzea.pizzabuy.data.database

import androidx.room.*
import com.barryzea.pizzabuy.data.model.Pizza
import com.barryzea.pizzabuy.data.model.PizzaAndTopics
import com.barryzea.pizzabuy.data.model.PizzaDetail
import com.barryzea.pizzabuy.data.model.Topic

/****
 * Project PizzaBuy
 * Created by Barry Zea H. on 24/11/2022.
 * Copyright (c)  All rights reserved.
 ***/
@Dao
interface MyDAO {
    @Transaction
    @Query("select * from Pizza")
    fun getPizzaAndTopics():List<PizzaAndTopics>

    @Query("select * from Pizza where pizzaId=:id")
    fun getPizzaById(id:Long):PizzaAndTopics

    @Insert
    fun savePizzaInCar(pizza:Pizza):Long

    @Insert
    fun saveTopics(topic: Topic)

    @Query("delete from Pizza")
    fun deleteAllPizzas()
    /*
    * Solo actualizaremos ingredientes de la pizza al ver el detalle del carrito de compras
    * */
    @Update
    fun updateTopics(topics:List<Topic>):Int
    /**************************************/
    @Insert
    fun saveDetail(detail:List<PizzaDetail>):List<Long>
    @Delete
    fun deleteItemOfCar(pizza:Pizza)





}
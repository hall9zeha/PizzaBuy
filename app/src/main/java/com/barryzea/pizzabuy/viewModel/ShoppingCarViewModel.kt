package com.barryzea.pizzabuy.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barryzea.pizzabuy.common.SingleMutableLiveData
import com.barryzea.pizzabuy.data.dataSource.RoomDatasourceInterface
import com.barryzea.pizzabuy.data.model.Pizza
import com.barryzea.pizzabuy.data.model.PizzaAndTopics
import com.barryzea.pizzabuy.data.model.PizzaDetail
import com.barryzea.pizzabuy.data.model.Topic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/****
 * Project PizzaBuy
 * Created by Barry Zea H. on 24/11/2022.
 * Copyright (c)  All rights reserved.
 ***/

@HiltViewModel
class ShoppingCarViewModel @Inject constructor (private val repository:RoomDatasourceInterface):ViewModel() {
    private var shoppingCarList = SingleMutableLiveData<List<PizzaAndTopics>>()

    private var _pizzaAndTopic = MutableLiveData<PizzaAndTopics>()
    val pizzaAndTopics=_pizzaAndTopic

    private var _idInserted= SingleMutableLiveData<Long>()
    val idInserted=_idInserted

    private var _idDetailInserted=SingleMutableLiveData<List<Long>>()
    val idDetailInserted=_idDetailInserted

    fun getPizzasAndTopics():MutableLiveData<List<PizzaAndTopics>>{
        viewModelScope.launch{
            shoppingCarList.postValue(repository.getPizzaAndTopics())

        }
        return shoppingCarList
    }
    fun getPizzaById(id:Long):MutableLiveData<PizzaAndTopics>{
        viewModelScope.launch {
            _pizzaAndTopic.postValue(repository.getPizzaById(id))
        }
        return pizzaAndTopics
    }
    fun savePizzaInCar(pizza: Pizza){
       viewModelScope.launch {
            _idInserted.postValue(repository.savePizza(pizza))
        }

    }

    fun saveTopic(topic:Topic){

        viewModelScope.launch {
            repository.saveTopic(topic)
        }

    }
    fun saveDetail(detail:List<PizzaDetail>){

        viewModelScope.launch {
             _idDetailInserted.postValue(repository.saveDetail(detail))
        }

    }
    fun updateTopics(topics:List<Topic>):Int{
        var listIdsOfRowsUpdated:Int?=null
        viewModelScope.launch{
            listIdsOfRowsUpdated = repository.updateTopics(topics)
        }
        return listIdsOfRowsUpdated?:0
    }
    fun deletePizza(pizza:Pizza){
        viewModelScope.launch {
            repository.deletePizzaOfCar(pizza)
        }
    }
    fun clearCarList(){
        viewModelScope.launch {
            repository.clearCarList()
        }
    }

}
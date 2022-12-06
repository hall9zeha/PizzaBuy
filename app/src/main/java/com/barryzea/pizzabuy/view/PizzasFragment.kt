package com.barryzea.pizzabuy.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.barryzea.pizzabuy.common.Constant
import com.barryzea.pizzabuy.common.startActivity
import com.barryzea.pizzabuy.data.dataSource.PizzaDatasource
import com.barryzea.pizzabuy.data.model.PizzaAndTopics
import com.barryzea.pizzabuy.data.model.PizzaDetail


import com.barryzea.pizzabuy.databinding.FragmentPizzasBinding
import com.barryzea.pizzabuy.view.adapters.PizzaAdapter
import com.barryzea.pizzabuy.viewModel.ShoppingCarViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PizzasFragment : Fragment() {
    private var bind:FragmentPizzasBinding?=null
    private lateinit var adapter:PizzaAdapter
    private val shoppingCarViewModel:ShoppingCarViewModel by viewModels()
    private var pizzaModel:PizzaAndTopics?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let{
            bind = FragmentPizzasBinding.inflate(inflater,container,false)
            return bind?.root
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter()
        adapter.addAll(PizzaDatasource.getPizzas())
        setUpViewModel()


    }

    private fun setUpAdapter()= with(bind){
        this?.let{
            adapter=PizzaAdapter(addToCar = ::addPizzaToCar, onClick = ::goToDetail, originScreen = Constant.MAIN_LIST)
            rvPizzas.setHasFixedSize(true)
            rvPizzas.layoutManager=LinearLayoutManager(context)
            rvPizzas.adapter=adapter
        }

    }

    private fun addPizzaToCar(pizzaAndTopics: PizzaAndTopics) {
        pizzaModel=pizzaAndTopics
        pizzaModel?.pizza?.totalPrice=pizzaAndTopics.pizza.price
        try{
        shoppingCarViewModel.savePizzaInCar(pizzaModel?.pizza?:pizzaAndTopics.pizza)
            Toast.makeText(context, "Agregado al carrito", Toast.LENGTH_SHORT).show()
            shoppingCarViewModel.getPizzasAndTopics()

        }
        catch(e:Exception){
            Log.e("ADD_TO_CAR_ERROR", e.message.toString())

        }

    }
    private fun goToDetail(pizzaAndTopics:PizzaAndTopics){
        requireActivity().startActivity<DetailActivity> {
            putExtra(Constant.MODEL_PIZZA_EXTRA,pizzaAndTopics)
        }
    }

    private fun setUpViewModel(){
        //Usamos este observador de las pizzas guardadas en el carrito de compras para ponerle el número al badge del botoom navigation
        //en la main activity
        shoppingCarViewModel.getPizzasAndTopics().observe(viewLifecycleOwner) {listCar->
            (activity as MainActivity)?.let{it.setUpBadgeShoppingCar(listCar.size)}
        }
        shoppingCarViewModel.idInserted.observe(viewLifecycleOwner, Observer(::saveDetail))
        shoppingCarViewModel.idDetailInserted.observe(viewLifecycleOwner,Observer(::saveTopics))

    }
    private fun saveDetail(idPizzaInserted:Long){
        pizzaModel?.let{
            val detailList= arrayListOf<PizzaDetail>()
            it.detail.forEach { det->

                det.detail.pizzaId=idPizzaInserted
                det.detail.namePizza= pizzaModel?.pizza!!.name
                detailList.add(det.detail)
           }
            shoppingCarViewModel.saveDetail(detailList)

        }
    }
    private fun saveTopics(idInsertedList:List<Long>?){

        pizzaModel?.let{pizzaAntTopic->
        idInsertedList?.let{listIds->
            listIds.forEach {id->
                pizzaAntTopic.detail.forEach {topicList->
                        topicList.topics.forEach {topic->
                            topic.id=0
                            topic.nameOfPizza=pizzaModel?.pizza!!.name
                            topic.topicPizzaId=id
                            shoppingCarViewModel.saveTopic(topic)
                        }
                    }
                }
            }

        }
    }



}
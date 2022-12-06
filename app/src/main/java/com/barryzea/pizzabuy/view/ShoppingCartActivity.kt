package com.barryzea.pizzabuy.view

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.accept
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.barryzea.pizzabuy.R
import com.barryzea.pizzabuy.common.Constant
import com.barryzea.pizzabuy.common.startActivity
import com.barryzea.pizzabuy.data.model.PizzaAndTopics
import com.barryzea.pizzabuy.databinding.ActivityShoppingCartBinding
import com.barryzea.pizzabuy.databinding.DialogShopLayoutBinding
import com.barryzea.pizzabuy.view.adapters.ShoppingCarAdapter
import com.barryzea.pizzabuy.viewModel.ShoppingCarViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Math.round

@AndroidEntryPoint
class ShoppingCartActivity : AppCompatActivity() {
    private lateinit var bind:ActivityShoppingCartBinding
    private val viewModel: ShoppingCarViewModel by viewModels()
    private lateinit var adapter: ShoppingCarAdapter
    private var beginCredits=50
    private var clickNum=0
    private var totalPayment=0.0
    private var carIsEmpty=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.elevation=0F
        title=getString(R.string.shopping_car)
        bind= ActivityShoppingCartBinding.inflate(layoutInflater)
        setContentView(bind.root)
        setUpAdapter()
        setUpListeners()
        setUpViewModel()
    }
    private fun setUpAdapter()=with(bind){
        this?.let{
            adapter=ShoppingCarAdapter(onClick = ::goToDetail, onDelete = ::deleteItemCar)
            rvShoppingCar.setHasFixedSize(true)
            rvShoppingCar.layoutManager= LinearLayoutManager(this@ShoppingCartActivity)
            rvShoppingCar.adapter=adapter

        }
    }
    private fun setUpListeners(){
        bind.fabPay.setOnClickListener {
            if(!carIsEmpty){
            if(beginCredits<totalPayment){
                if(clickNum==0) {
                    Toast.makeText(
                        this,
                        "da click 5 veces en el botón de pagar para ganar créditos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else if(clickNum==5){
                    beginCredits +=10
                    bind.tvCredits.text=beginCredits.toString()
                    clickNum=0
                }
                clickNum++
            }
            else{
                try {
                    viewModel.clearCarList()
                    showDialog()
                    clearViews()

                }catch(e:Exception){
                    Log.e("ERROR_AL_ELIMINAR", e.message.toString())
                }
            }
        }
        }
    }
    private fun deleteItemCar(pizzaAndTopics: PizzaAndTopics) {
        viewModel.deletePizza(pizzaAndTopics.pizza)
        adapter.remove(pizzaAndTopics)
    }

    private fun goToDetail(pizzaAndTopics: PizzaAndTopics){
        startActivity<CarDetailActivity> {
            putExtra(Constant.MODEL_PIZZA_EXTRA,pizzaAndTopics)
        }
    }
    private fun setUpViewModel(){
        viewModel.getPizzasAndTopics().observe(this) {
            it?.let { list ->
                if(list.isNotEmpty())carIsEmpty=false

                adapter.addAll(list)
                setUpDetails(list)
            }

        }
    }

    private fun showDialog(){

        MaterialAlertDialogBuilder(this)
            .setView(R.layout.dialog_shop_layout)
            .setPositiveButton(R.string.accept
            ) { dialog, _ -> dialog.dismiss();finish() }
            .show()

    }
    private fun setUpDetails(pizzaAndTopics: List<PizzaAndTopics>){
        pizzaAndTopics.forEach {
            totalPayment += it.pizza.totalPrice
        }
        bind.tvCredits.text=String.format("créditos: %s",beginCredits)
        bind.tvTotal.text=String.format("Total a pagar: $. %.2f",totalPayment.toFloat())
    }
    private fun clearViews(){
        adapter.removeAll()
        bind.tvCredits.text=String.format("Créditos: %s",50)
        bind.tvTotal.text=String.format("Total a pagar: $. %.2f",0.0)
        carIsEmpty=true
    }
}
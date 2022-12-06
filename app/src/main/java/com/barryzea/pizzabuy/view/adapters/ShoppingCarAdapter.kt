package com.barryzea.pizzabuy.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.barryzea.pizzabuy.MyApp
import com.barryzea.pizzabuy.R
import com.barryzea.pizzabuy.common.loadUrl
import com.barryzea.pizzabuy.data.model.PizzaAndTopics
import com.barryzea.pizzabuy.databinding.ShoppingCarItemBinding

/****
 * Project PizzaBuy
 * Created by Barry Zea H. on 24/11/2022.
 * Copyright (c)  All rights reserved.
 ***/

class ShoppingCarAdapter(private val onClick:(PizzaAndTopics)->Unit, private val onDelete:(PizzaAndTopics)->Unit):RecyclerView.Adapter<ShoppingCarAdapter.ViewHolder>() {
    private val shoppingCarList = arrayListOf<PizzaAndTopics>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context=parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.shopping_car_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shoppingCarList[position],onClick,onDelete)
    }
    fun addAll(pizzaAndTopics: List<PizzaAndTopics>){
        pizzaAndTopics.forEach {
            if (!shoppingCarList.contains(it)) {
                shoppingCarList.add(it)
                notifyItemInserted(shoppingCarList.size - 1)
            }
            else{
                val index=shoppingCarList.indexOf(it)
                shoppingCarList[index] = it
                notifyItemChanged(index)
            }
        }
    }
    fun remove(pizzaAndTopics: PizzaAndTopics){
        if(shoppingCarList.contains(pizzaAndTopics)){
            val position=shoppingCarList.indexOf(pizzaAndTopics)
            shoppingCarList.remove(pizzaAndTopics)
            notifyItemRemoved(position)
        }
    }
    fun removeAll() {
        if(shoppingCarList.isNotEmpty()){
            val sizeList=shoppingCarList.size
            shoppingCarList.clear()
            notifyItemRangeRemoved(0,sizeList)

        }
    }
    override fun getItemCount()=shoppingCarList.size

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private val binding=ShoppingCarItemBinding.bind(itemView)
        fun bind(
            model: PizzaAndTopics,
            onClick: (PizzaAndTopics) -> Unit,
            onDelete: (PizzaAndTopics) -> Unit
        )=with(binding){
            ivCarPizza.loadUrl(model.pizza.urlImage)
            tvCarName.text=model.pizza.name
            tvCarPrice.text=String.format("$%.2f ",model.pizza.totalPrice)
            tvQuantity.text=model.pizza.quantity.toString()
            this.root.setOnClickListener { onClick(model) }
            btnCarDelete.setOnClickListener { onDelete(model) }
        }
    }
}
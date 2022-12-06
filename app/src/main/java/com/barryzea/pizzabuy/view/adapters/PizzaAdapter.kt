package com.barryzea.pizzabuy.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.barryzea.pizzabuy.common.Constant
import com.barryzea.pizzabuy.common.loadUrl
import com.barryzea.pizzabuy.data.model.Pizza
import com.barryzea.pizzabuy.data.model.PizzaAndTopics
import com.barryzea.pizzabuy.databinding.PizzaItemBinding

/****
 * Project PizzaBuy
 * Created by Barry Zea H. on 22/11/2022.
 * Copyright (c)  All rights reserved.
 ***/

class PizzaAdapter(private val originScreen:Int,private val onClick:(PizzaAndTopics)->Unit, private val addToCar:(PizzaAndTopics)->Unit): RecyclerView.Adapter<PizzaAdapter.ViewHolder>(), Filterable {
    private var pizzasList:MutableList<PizzaAndTopics> = arrayListOf()
    private var pizzasListFiltered:MutableList<PizzaAndTopics> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context=parent.context
        val itemView=PizzaItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(itemView.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pizzasListFiltered[position],onClick,addToCar,originScreen)
    }

    override fun getItemCount() = pizzasListFiltered.size
    fun addAll(list:List<PizzaAndTopics>){
        list.forEach {
            add(it)
        }
    }
    private fun add(pizza:PizzaAndTopics){
        if(!pizzasList.contains(pizza)){
            pizzasList.add(pizza)
            pizzasListFiltered.add(pizza)
            val position=pizzasList.size -1
            notifyItemInserted(position)
        }
    }
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private val binding=PizzaItemBinding.bind(itemView)
        fun bind(model: PizzaAndTopics, onClick: (PizzaAndTopics) -> Unit,addToCar:(PizzaAndTopics)->Unit, originScreen:Int)=with(binding){

            ivPizza.loadUrl(model.pizza.urlImage)
            tvName.text=model.pizza.name
            tvPrice.text=String.format("$%.2f",model.pizza.price)
            this.root.setOnClickListener { onClick(model) }
            btnAddCar.setOnClickListener { addToCar(model) }
            btnCustom.setOnClickListener { onClick(model) }
            if(originScreen==Constant.DIALOG_LIST){
                btnCustom.visibility=View.GONE
                btnAddCar.visibility=View.GONE
                tvName.textSize=22F
            }

        }
    }

    override fun getFilter(): Filter {
        return object :Filter(){
            override fun performFiltering(searchText: CharSequence?): FilterResults {
                val charString=searchText?.toString()?:""
                if(charString.isEmpty()) pizzasListFiltered = pizzasList else{
                    val filteredList = ArrayList<PizzaAndTopics>()
                    pizzasList
                        .filter {
                            (it.pizza.name.lowercase().contains(searchText!!.toString().lowercase()) )
                        }
                        .forEach { filteredList.add(it) }
                    pizzasListFiltered=filteredList
                }
                return FilterResults().apply { values=pizzasListFiltered }
            }

            override fun publishResults(searchText: CharSequence?, results: FilterResults?) {
                pizzasListFiltered = if(results?.values==null)
                    ArrayList()
                else
                    results.values as ArrayList<PizzaAndTopics>
                notifyDataSetChanged()

            }
        }
    }
}
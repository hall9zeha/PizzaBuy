package com.barryzea.pizzabuy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.barryzea.pizzabuy.R
import com.barryzea.pizzabuy.common.Constant
import com.barryzea.pizzabuy.common.startActivity
import com.barryzea.pizzabuy.data.dataSource.PizzaDatasource
import com.barryzea.pizzabuy.data.model.PizzaAndTopics
import com.barryzea.pizzabuy.databinding.FragmentSearchBinding
import com.barryzea.pizzabuy.view.adapters.PizzaAdapter
import com.barryzea.pizzabuy.viewModel.ShoppingCarViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var bind:FragmentSearchBinding?=null
    private lateinit var adapter:PizzaAdapter
    private val viewModel:ShoppingCarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       activity?.let{
           bind= FragmentSearchBinding.inflate(inflater, container, false)
           bind?.let{
               return it.root
           }
       }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpListener()
        setUpAdapter()

    }

    private fun setUpListener()=with(bind) {
        this?.let{
            searchMain.setOnQueryTextListener(object:androidx.appcompat.widget.SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    adapter.filter.filter(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter.filter(newText)
                    return false
                }
            })
        }
    }
    private fun setUpAdapter()=with(bind){
        this?.let{
            adapter= PizzaAdapter(Constant.MAIN_LIST, onClick = ::goToDetail, addToCar = {})
            adapter.addAll(PizzaDatasource.getPizzas())
            rvSearch.setHasFixedSize(true)
            rvSearch.layoutManager=LinearLayoutManager(context)
            rvSearch.adapter=adapter

        }

    }
    private fun goToDetail(pizzaAndTopics: PizzaAndTopics){
        activity?.startActivity<DetailActivity> {
            putExtra(Constant.MODEL_PIZZA_EXTRA,pizzaAndTopics)
        }
    }

}
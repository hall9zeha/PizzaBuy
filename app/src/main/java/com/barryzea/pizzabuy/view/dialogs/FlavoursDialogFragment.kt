package com.barryzea.pizzabuy.view.dialogs

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.barryzea.pizzabuy.common.Constant
import com.barryzea.pizzabuy.data.dataSource.PizzaDatasource
import com.barryzea.pizzabuy.data.model.PizzaAndTopics
import com.barryzea.pizzabuy.databinding.FragmentDialogFlavoursBinding
import com.barryzea.pizzabuy.view.DetailActivity
import com.barryzea.pizzabuy.view.adapters.PizzaAdapter


class FlavoursDialogFragment : DialogFragment() {

    private var bind:FragmentDialogFlavoursBinding?=null
    private lateinit var adapter:PizzaAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let{
            bind=FragmentDialogFlavoursBinding.inflate(inflater,container,false)
            return bind!!.root
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    private fun setWidthPercent(percentage: Int) {
        val percent = percentage.toFloat() / 100
        val dm = Resources.getSystem().displayMetrics
        val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
        val percentWidth = rect.width() * percent
        dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter()
    }
    private fun setUpAdapter()=with(bind){
        this?.let{
            adapter= PizzaAdapter(onClick =::addSelectionInList,originScreen= Constant.DIALOG_LIST, addToCar={})
            rvFlavours.setHasFixedSize(true)
            rvFlavours.layoutManager=LinearLayoutManager(context)
            rvFlavours.adapter=adapter
            adapter.addAll(PizzaDatasource.getPizzas())
        }
    }
    private fun addSelectionInList(pizzaAndTopics: PizzaAndTopics){
        pizzaAndTopics.detail[0].detail.namePizza=pizzaAndTopics.pizza.name
        (activity as DetailActivity).setUpTopics(pizzaAndTopics)
        (activity as DetailActivity).changeDetailViewsValues(true)
        dismiss()
    }
    override fun onResume() {
        super.onResume()
        setWidthPercent(95)
    }

}
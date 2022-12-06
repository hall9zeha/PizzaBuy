package com.barryzea.pizzabuy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import com.barryzea.pizzabuy.R
import com.barryzea.pizzabuy.common.Constant
import com.barryzea.pizzabuy.common.loadUrl
import com.barryzea.pizzabuy.data.model.*
import com.barryzea.pizzabuy.databinding.ActivityDetailBinding
import com.barryzea.pizzabuy.view.dialogs.FlavoursDialogFragment
import com.barryzea.pizzabuy.viewModel.ShoppingCarViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var bind:ActivityDetailBinding
    private lateinit var model:PizzaAndTopics
    private val viewModel:ShoppingCarViewModel by viewModels()
    private var topicList= arrayListOf<Topic>()
    private var pizzaDetail= arrayListOf<PizzaDetail>()
    private var numOfTopicsSelected=0
    var detailAndTopicsList= arrayListOf<DetailAndTopicsRef>()
    private var indexOfTopic=0
    private var groupTopicsSize=4
    var idCheckBox=1
    var idTextView=1
    private var initPrice=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.elevation=0F
        title = "Detalle"
        bind= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bind.root)
        model = intent?.extras?.getParcelable(Constant.MODEL_PIZZA_EXTRA)?: PizzaAndTopics()
        initPrice=model.pizza.price
        setUpDetail(model)
        setUpViewModel()
        setUpListeners()
    }


    private fun setUpDetail(model: PizzaAndTopics)=with(bind){
        tvNameDetail.text=model.pizza.name
        tvDesc.text=model.pizza.description
        tvPriceDetail.text=String.format("$%.2f",model.pizza.price)

        ivDetail.loadUrl(model.pizza.urlImage)
        model.detail[0].detail.namePizza=model.pizza.name
        setUpTopics(model)
        setNewQuantity(model)

    }
    fun setUpTopics(detailAndTopics: PizzaAndTopics)=with(bind){
        numOfTopicsSelected=0
        lnTopics.removeAllViews()

        pizzaDetail.clear()
        topicList.clear()

        detailAndTopicsList.addAll(detailAndTopics.detail)

        detailAndTopicsList.forEach {

            pizzaDetail.add(it.detail)

            val tvNamePizza=TextView(this@DetailActivity)
            tvNamePizza.textSize=20F
            tvNamePizza.id=idTextView
            tvNamePizza.text=it.detail.namePizza
            tvNamePizza.typeface=ResourcesCompat.getFont(this@DetailActivity,R.font.carter_one)

            lnTopics.addView(tvNamePizza)

            it.topics.forEach { topic ->

                val chkTopic = CheckBox(this@DetailActivity)
                chkTopic.id = idCheckBox
                chkTopic.text = topic.nameOfTopic
                chkTopic.isChecked = topic.state
                chkTopic.setPadding(80, 0, 0, 0)
                chkTopic.textSize = 18F
                chkTopic.typeface=ResourcesCompat.getFont(this@DetailActivity,R.font.carter_one)

                lnTopics.addView(chkTopic)
                //llenamos la lista de ingredientes al cargar los detalles
                topicList.add(topic)
                chkTopic.setOnCheckedChangeListener { _, isChecked ->
                    if (!isChecked) numOfTopicsSelected-- else numOfTopicsSelected++
                    //Comprobamos que no se desmarquen más de dos ingredientes
                    if (numOfTopicsSelected <= 1) {
                        if (!isChecked) {
                            Toast.makeText(
                                this@DetailActivity,
                                "mínimo dos ingredientes",
                                Toast.LENGTH_SHORT
                            ).show()
                            chkTopic.isChecked = true
                        }
                        numOfTopicsSelected++
                    }
                    topic.state = isChecked
                    saveStateTopicsInList(topic)
                }
                idCheckBox++
                idTextView++
                numOfTopicsSelected++
            }
        }
    }
    private fun saveStateTopicsInList(topic:Topic){

        if(topicList.contains(topic)){
            val index=topicList.indexOf(topic)
            topicList[index] = topic

        }else{
            topicList.add(topic)
        }

    }
    private fun setUpViewModel(){

        viewModel.idInserted.observe(this, Observer(::saveDetail))
        viewModel.idDetailInserted.observe(this, Observer(::saveTopics))
    }
    private fun setUpListeners()=with(bind){
        fabAddCar.setOnClickListener{
            addPizzaToCar(model.pizza)
        }
        swMoreFlavors.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                setVisibilityViews(View.VISIBLE)
                changeDetailViewsValues(true)

            }else{
               setVisibilityViews(View.GONE)
                clearFlavorsSelected()
                changeDetailViewsValues(false)

            }
        }
        btnAddFlavor.setOnClickListener {
            if(detailAndTopicsList.size >=4){
                Toast.makeText(this@DetailActivity, "Solo puedes poner cuatro sabores", Toast.LENGTH_SHORT).show()
            }
            else{
                FlavoursDialogFragment().show(supportFragmentManager,FlavoursDialogFragment::class.simpleName)
            }
        }
        btnMore.setOnClickListener {
           if(model.pizza.quantity <=model.pizza.quantity){
               model.pizza.quantity +=1
               setNewQuantity(model)
           }
        }
        btnLess.setOnClickListener {
            if(model.pizza.quantity>1){
                model.pizza.quantity -=1
                setNewQuantity(model)
            }
        }


    }
    private fun clearFlavorsSelected(){
        detailAndTopicsList.clear()
        idCheckBox=1;idTextView=1;numOfTopicsSelected=0
        setUpDetail(model)

    }
    fun changeDetailViewsValues(isCombined:Boolean){

        if(isCombined){
            bind.tvNameDetail.text=if(detailAndTopicsList.size<=1)"${detailAndTopicsList.size} sabor" else "${detailAndTopicsList.size} sabores"
            bind.tvDesc.text= getString(R.string.pizzaCombinedDescription)
            if(detailAndTopicsList.size>1){
                bind.tvPriceDetail.text=30.00.toString()
                model.pizza.price=30.00
            }
            setNewQuantity(model)
        }
        else{
            bind.tvNameDetail.text=model.pizza.name
            bind.tvDesc.text= model.pizza.description
            bind.tvPriceDetail.text=initPrice.toString()
            model.pizza.price=initPrice
            setNewQuantity(model)

        }
    }
    private fun setNewQuantity(model: PizzaAndTopics){
        bind.tvQuantity.text=model.pizza.quantity.toString()
        var totalPrice=model.pizza.price * model.pizza.quantity
        model.pizza.totalPrice=totalPrice
        var totalStr=getString(R.string.total_price,totalPrice.toString().toDouble()/*,model.pizza.quantity,model.pizza.price*/)
        bind.tvTotal.text=HtmlCompat.fromHtml(totalStr,HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
    private fun setVisibilityViews(state:Int)=with(bind){
        btnAddFlavor.visibility= state
        tvAddFlavor.visibility=state
    }
    private fun addPizzaToCar(pizza: Pizza)=with(bind){
           pizza.pizzaId=0
           pizza.name=tvNameDetail.text.toString()
           pizza.description = tvDesc.text.toString()
           pizza.urlImage = pizza.urlImage
        viewModel.savePizzaInCar(pizza)

    }
    private fun saveDetail(idPizzaInserted:Long?){

        idPizzaInserted?.let{
            pizzaDetail.forEach {
                it.pizzaId=idPizzaInserted

            }
            viewModel.saveDetail(pizzaDetail)
            Toast.makeText(this, "Guardado en el carrito", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
    private fun saveTopics(idDetailInserted: List<Long>?) {
        idDetailInserted?.let{

            it.forEach { id->

                   for (index in indexOfTopic until groupTopicsSize) {

                    val topic=topicList[index]
                    topic.id=0
                    topic.topicPizzaId =id
                    topic.nameOfPizza=model.pizza.name
                    viewModel.saveTopic(topic)

            }
                groupTopicsSize +=4
                indexOfTopic +=4

        }
        }

    }
}
package com.barryzea.pizzabuy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import com.barryzea.pizzabuy.R
import com.barryzea.pizzabuy.common.Constant
import com.barryzea.pizzabuy.common.loadUrl
import com.barryzea.pizzabuy.data.model.Pizza
import com.barryzea.pizzabuy.data.model.PizzaAndTopics
import com.barryzea.pizzabuy.data.model.Topic
import com.barryzea.pizzabuy.databinding.ActivityCarDetailBinding
import com.barryzea.pizzabuy.databinding.ActivityDetailBinding
import com.barryzea.pizzabuy.viewModel.ShoppingCarViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarDetailActivity : AppCompatActivity() {
    private lateinit var bind:ActivityCarDetailBinding
    private lateinit var model:PizzaAndTopics
    private var topicList = arrayListOf<Topic>()
    private val viewModel:ShoppingCarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        title = "Detalle carrito"
        super.onCreate(savedInstanceState)
        supportActionBar?.elevation=0F
        bind=ActivityCarDetailBinding.inflate(layoutInflater)
        setContentView(bind.root)

        model = intent?.extras?.getParcelable(Constant.MODEL_PIZZA_EXTRA)?: PizzaAndTopics()
        setUpListeners()
        setUpViewModel()

    }
    private fun setUpViewModel(){
        viewModel.getPizzaById(model.pizza.pizzaId).observe(this){
            setUpDetail(it)
        }
    }

    private fun setUpDetail(model: PizzaAndTopics)=with(bind){
        tvNameDetail.text=model.pizza.name
        tvDesc.text=model.pizza.description
        tvPriceDetail.text=String.format("%.2f",model.pizza.totalPrice)
        ivDetail.loadUrl(model.pizza.urlImage)
        tvQuantity.text=String.format("Cantidad: %s", model.pizza.quantity)
        var idView=1
        var idTextView=1

        model.detail.forEach { data->
            val tvNamePizza= TextView(this@CarDetailActivity)
            tvNamePizza.textSize=20F
            tvNamePizza.id=idTextView
            tvNamePizza.text=data.detail.namePizza
            tvNamePizza.typeface=ResourcesCompat.getFont(this@CarDetailActivity,R.font.carter_one)
            lnTopics.addView(tvNamePizza)

            data.topics.forEach { topic ->
                val chkTopic = CheckBox(this@CarDetailActivity)
                chkTopic.id = idView
                chkTopic.text = topic.nameOfTopic
                chkTopic.isChecked = topic.state
                chkTopic.setPadding(80, 0, 0, 0)
                chkTopic.textSize = 18F
                chkTopic.typeface= ResourcesCompat.getFont(this@CarDetailActivity,R.font.carter_one)

                lnTopics.addView(chkTopic)
                //llenamos la lista de ingredientes al cargar los detalles
                topicList.add(topic)
                chkTopic.setOnClickListener {
                    topic.state = chkTopic.isChecked
                    if (topicList.contains(topic)) {
                        val index = topicList.indexOf(topic)
                        topicList[index] = topic

                    } else {
                        topicList.add(topic)
                    }
                }
                idView++
                idTextView ++

            }
        }
    }
    private fun setUpListeners()=with(bind){
        fabUpdateCar.setOnClickListener{
           updateTopics(topicList)
        }

    }
    private fun updateTopics(list: List<Topic>){
        var idsOfRowsUpdated = 0
        try {
            idsOfRowsUpdated=viewModel.updateTopics(list)
            Toast.makeText(this@CarDetailActivity, "se modificó correctamente", Toast.LENGTH_SHORT).show()
        }
        catch(e:Exception){
            Toast.makeText(this@CarDetailActivity, "Error", Toast.LENGTH_SHORT).show()
        }

        if(idsOfRowsUpdated !=0){
            Toast.makeText(this@CarDetailActivity, idsOfRowsUpdated.toString(), Toast.LENGTH_SHORT).show()
        }
    }


}
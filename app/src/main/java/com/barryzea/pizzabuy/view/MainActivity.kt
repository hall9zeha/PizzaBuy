package com.barryzea.pizzabuy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.barryzea.pizzabuy.R
import com.barryzea.pizzabuy.databinding.ActivityMainBinding
import com.barryzea.pizzabuy.viewModel.ShoppingCarViewModel
import com.google.android.material.badge.BadgeDrawable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    private lateinit var navcontroller: NavController
    private val viewModel:ShoppingCarViewModel by  viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_PizzaBuy)
        super.onCreate(savedInstanceState)
        supportActionBar?.elevation=0F
        bind= ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        setUpNavigation()
        setupViewModel()
    }
    private fun setUpNavigation(){
        navcontroller=(supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        NavigationUI.setupWithNavController(bind.bottomNavMain.bottomNav,navcontroller)

        //ponemos títulos en el toolbar cuando naveguemos a cada fragmento
        navcontroller.addOnDestinationChangedListener{controller, destination,arguments->
            title=when(destination.id){
                R.id.itemHome->"Pizzas"
                R.id.itemCar->"Carrito"
                R.id.itemSearch->"Buscar"
                else->"PizzaBuy"
            }
        }
    }
    private fun setupViewModel(){
        viewModel.getPizzasAndTopics().observe(this){
            setUpBadgeShoppingCar(it.size)
        }
    }
    fun setUpBadgeShoppingCar(itemsCount:Int?){

        var itemCarBadge:BadgeDrawable = bind.bottomNavMain.bottomNav.getOrCreateBadge(R.id.itemCar)
        itemCarBadge.number=itemsCount?:0
        itemCarBadge.maxCharacterCount=3
        itemCarBadge.backgroundColor=ContextCompat.getColor(this,R.color.color_badge)
        itemCarBadge.badgeTextColor=ContextCompat.getColor(this,R.color.yellow_200)

    }

    override fun onResume() {
        super.onResume()
        viewModel.getPizzasAndTopics()
    }
}
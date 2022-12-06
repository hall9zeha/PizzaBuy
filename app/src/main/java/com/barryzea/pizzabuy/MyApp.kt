package com.barryzea.pizzabuy

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

/****
 * Project PizzaBuy
 * Created by Barry Zea H. on 22/11/2022.
 * Copyright (c)  All rights reserved.
 ***/
@HiltAndroidApp
class MyApp:Application() {
    companion object{
        lateinit var  context:Context

    }
    override fun onCreate() {
        super.onCreate()
        context=this
    }
}
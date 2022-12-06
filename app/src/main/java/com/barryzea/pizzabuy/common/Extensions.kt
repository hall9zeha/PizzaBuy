package com.barryzea.pizzabuy.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.ImageView
import com.barryzea.pizzabuy.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/****
 * Project PizzaBuy
 * Created by Barry Zea H. on 22/11/2022.
 * Copyright (c)  All rights reserved.
 ***/


fun ImageView.loadUrl(url:String){
    val request= RequestOptions
        .diskCacheStrategyOf(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.pizza)
        .centerCrop()
        .error(R.drawable.pizza)
    Glide.with(this.context)
        .load(url)
        .apply(request)
        .into(this)
}
inline fun <reified T:Activity> Context.intentFor(body : Intent.()->Unit):Intent=
    Intent(this,T::class.java).apply(body)

inline fun <reified T: Activity> Context.startActivity(body:Intent.()->Unit){
    startActivity(intentFor<T>(body))
}
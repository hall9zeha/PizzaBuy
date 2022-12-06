package com.barryzea.pizzabuy.common

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/****
 * Project PizzaBuy
 * Created by Barry Zea H. on 01/12/2022.
 * Copyright (c)  All rights reserved.
 ***/

  class SingleMutableLiveData<T>: MutableLiveData<T>() {
        private val pending = AtomicBoolean(false)

        @MainThread
        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            super.observe(owner, Observer{t->
                if(t != null){
                    observer.onChanged(t)
                    postValue(null)
                }
            })
        }
    }

package com.ubaya.ubayakuliner_160419003_160419038.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.ubayakuliner_160419003_160419038.model.Cart
import com.ubaya.ubayakuliner_160419003_160419038.model.Food
import com.ubaya.ubayakuliner_160419003_160419038.util.buildDb
import com.ubaya.ubayakuliner_160419003_160419038.util.userId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailRestaurantViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val foodLiveData = MutableLiveData<List<Food>>()
    val foodLoadErrorLiveData = MutableLiveData<Boolean>()
    val foodLoadingLiveData = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun fetch(restoId:Int) {
        foodLoadErrorLiveData.value = false
        foodLoadingLiveData.value = true
        launch {
            val db = buildDb(getApplication())
            foodLiveData.value = db.foodDao().selectAll(restoId)
        }
    }

    fun insertCart(cart: Cart) {
        launch {
            val db = buildDb(getApplication())
            db.cartDao().insert(cart)
        }
    }
}
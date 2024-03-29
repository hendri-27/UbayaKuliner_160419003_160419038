package com.ubaya.ubayakuliner_160419003_160419038.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ubaya.ubayakuliner_160419003_160419038.model.*
import com.ubaya.ubayakuliner_160419003_160419038.util.buildDb
import com.ubaya.ubayakuliner_160419003_160419038.util.userId
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DetailRestaurantViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val foodLiveData = MutableLiveData<ArrayList<FoodWithCart>>()
    val foodLoadErrorLiveData = MutableLiveData<Boolean>()
    val foodLoadingLiveData = MutableLiveData<Boolean>()
    val restaurantLiveData = MutableLiveData<Restaurant>()
    val restaurantLoadErrorLiveData = MutableLiveData<Boolean>()
    val restaurantLoadingLiveData = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun fetchFoodWithCart(restoId:Int) {
        foodLoadErrorLiveData.value = false
        foodLoadingLiveData.value = true
        launch {
            val db = buildDb(getApplication())
            foodLiveData.value = ArrayList(db.foodDao().select(userId, restoId))
        }
        foodLoadingLiveData.value = false
    }

    fun fetchRestaurant(resto_id: Int) {
        restaurantLoadErrorLiveData.value = false
        restaurantLoadingLiveData.value = true
        launch {
            val db = buildDb(getApplication())
            restaurantLiveData.value = db.restaurantDao().select(resto_id)
        }
        restaurantLoadingLiveData.value = false
    }

    fun validateCart(restoId:Int):Boolean{
        var userCart:ArrayList<CartWithFood> = ArrayList()

        runBlocking {
            launch {
                val db = buildDb(getApplication())
                userCart = ArrayList(db.cartDao().select(userId))
            }
        }

        var validate = true
        if (userCart.isNotEmpty()){
            if (userCart[0].food.restaurantId != restoId){
                validate = false
            }
        }
        return validate
    }

    fun insertCart(cart: Cart) {
        launch {
            val db = buildDb(getApplication())
            db.cartDao().insert(cart)
        }
    }


    fun updateCart(food_id: Int, quantity: Int) {
        launch {
            val db = buildDb(getApplication())
            db.cartDao().update(userId, food_id, quantity)
        }
    }

    fun deleteCart(cart: Cart) {
        launch {
            val db = buildDb(getApplication())
            db.cartDao().delete(cart)
        }
    }
}
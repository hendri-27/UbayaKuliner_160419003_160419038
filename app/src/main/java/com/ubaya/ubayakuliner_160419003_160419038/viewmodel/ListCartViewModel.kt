package com.ubaya.ubayakuliner_160419003_160419038.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ubaya.ubayakuliner_160419003_160419038.model.Cart
import com.ubaya.ubayakuliner_160419003_160419038.model.CartWithFood
import com.ubaya.ubayakuliner_160419003_160419038.util.buildDb
import com.ubaya.ubayakuliner_160419003_160419038.util.userId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListCartViewModel(application: Application) : AndroidViewModel(application), CoroutineScope  {
    val cartLiveData = MutableLiveData<ArrayList<CartWithFood>>()
    val cartLoadErrorLiveData = MutableLiveData<Boolean>()
    val cartLoadingLiveData = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh() {
        cartLoadErrorLiveData.value = false
        cartLoadingLiveData.value = true
        launch { // Menjalankannya di thread coroutine
            val db = buildDb(getApplication())
            cartLiveData.value = ArrayList(db.cartDao().select(userId))
        }
        cartLoadingLiveData.value = false
    }

    fun update(food_id: Int, quantity: Int) {
        launch {
            val db = buildDb(getApplication())
            db.cartDao().update(userId, food_id, quantity)

            cartLiveData.value = ArrayList(db.cartDao().select(userId))
        }
    }

    fun delete(cart: Cart) {
        launch {
            val db = buildDb(getApplication())
            db.cartDao().delete(cart)

            cartLiveData.value = ArrayList(db.cartDao().select(userId))
        }
    }

    fun delete() {
        launch {
            val db = buildDb(getApplication())
            db.cartDao().delete(userId)
        }
    }
}
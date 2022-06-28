package com.ubaya.ubayakuliner_160419003_160419038.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ubaya.ubayakuliner_160419003_160419038.model.DetailTransaction
import com.ubaya.ubayakuliner_160419003_160419038.model.Restaurant
import com.ubaya.ubayakuliner_160419003_160419038.model.Transaction
import com.ubaya.ubayakuliner_160419003_160419038.model.User
import com.ubaya.ubayakuliner_160419003_160419038.util.buildDb
import com.ubaya.ubayakuliner_160419003_160419038.util.userId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CheckoutViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val userLiveData = MutableLiveData<User>()
    val userLoadErrorLiveData = MutableLiveData<Boolean>()
    val userLoadingLiveData = MutableLiveData<Boolean>()
    val restaurantLiveData = MutableLiveData<Restaurant>()
    val restaurantLoadErrorLiveData = MutableLiveData<Boolean>()
    val restaurantLoadingLiveData = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun fetchUser() {
        userLoadErrorLiveData.value = false
        userLoadingLiveData.value = true
        launch {
            val db = buildDb(getApplication())
            userLiveData.value = db.userDao().select(userId)
        }
        userLoadingLiveData.value = false
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

    fun placeOrder(transaction: Transaction, listDetailTransaction: ArrayList<DetailTransaction>) {
        launch {
            val db = buildDb(getApplication())
            db.transactionDao().insert(transaction)
            db.detailTransactionDao().insert(*listDetailTransaction.toList().toTypedArray())
        }
    }
}

package com.ubaya.ubayakuliner_160419003_160419038.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ubaya.ubayakuliner_160419003_160419038.model.Restaurant
import com.ubaya.ubayakuliner_160419003_160419038.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListRestaurantViewModel(application: Application) : AndroidViewModel(application) ,
    CoroutineScope {
    val restaurantLiveData = MutableLiveData<ArrayList<Restaurant>>()
    val restaurantLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    private var job = Job()
    override val coroutineContext: CoroutineContext get() = job + Dispatchers.Main

    fun refresh() {
        restaurantLoadErrorLiveData.value = false
        loadingLiveData.value = true
        launch {
            val db = buildDb(getApplication())
            restaurantLiveData.value = ArrayList(db.restaurantDao().selectAll())
        }
        loadingLiveData.value =false
    }
}
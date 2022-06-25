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
import com.ubaya.ubayakuliner_160419003_160419038.model.Review
import com.ubaya.ubayakuliner_160419003_160419038.model.ReviewWithUser
import com.ubaya.ubayakuliner_160419003_160419038.model.User
import com.ubaya.ubayakuliner_160419003_160419038.util.buildDb
import com.ubaya.ubayakuliner_160419003_160419038.util.userId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListReviewViewModel(application: Application) : AndroidViewModel(application),
    CoroutineScope {
    val reviewLiveData = MutableLiveData<ArrayList<ReviewWithUser>>()
    val reviewLoadErrorLiveData = MutableLiveData<Boolean>()
    val reviewloadingLiveData = MutableLiveData<Boolean>()

    private var job = Job()
    override val coroutineContext: CoroutineContext get() = job + Dispatchers.Main

    fun refresh(restoId:Int) {
        reviewLoadErrorLiveData.value = false
        reviewloadingLiveData.value = true

        launch {
            val db = buildDb(getApplication())
            reviewLiveData.value = db.reviewDao().select(restoId)
        }
    }

    fun insert(review:Review){
        launch {
            val db = buildDb(getApplication())
            db.reviewDao().insert(review)

            db.restaurantDao().update(review.restaurantId, review.rating)
        }
    }
}
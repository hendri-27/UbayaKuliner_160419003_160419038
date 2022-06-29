package com.ubaya.ubayakuliner_160419003_160419038.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ubaya.ubayakuliner_160419003_160419038.model.Review
import com.ubaya.ubayakuliner_160419003_160419038.model.ReviewWithUser
import com.ubaya.ubayakuliner_160419003_160419038.util.buildDb
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
            reviewLiveData.value = ArrayList(db.reviewDao().select(restoId))
        }
        reviewloadingLiveData.value = false
    }

    fun insert(review:Review){
        launch {
            val db = buildDb(getApplication())
            db.reviewDao().insert(review)
            db.transactionDao().updateRate(review.transactionId,review.rating)
            db.restaurantDao().update(review.restaurantId, review.rating)
        }
    }
}
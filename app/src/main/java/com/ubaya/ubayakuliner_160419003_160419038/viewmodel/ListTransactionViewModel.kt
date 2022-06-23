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
import com.ubaya.ubayakuliner_160419003_160419038.model.Transaction
import com.ubaya.ubayakuliner_160419003_160419038.model.TransactionWithRestaurant
import com.ubaya.ubayakuliner_160419003_160419038.util.buildDb
import com.ubaya.ubayakuliner_160419003_160419038.util.userId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListTransactionViewModel(application: Application) : AndroidViewModel(application),
    CoroutineScope {
    val transactionLiveData = MutableLiveData<List<Transaction>>()
    val transactionForReviewLiveData = MutableLiveData<TransactionWithRestaurant>()
    val transactionLoadErrorLiveData = MutableLiveData<Boolean>()
    val transactionForReviewLoadErrorLiveData = MutableLiveData<Boolean>()
    val transactionloadingLiveData = MutableLiveData<Boolean>()
    val transactionForReviewloadingLiveData = MutableLiveData<Boolean>()

    private var job = Job()
    override val coroutineContext: CoroutineContext get() = job + Dispatchers.Main

    fun refresh() {
        transactionLoadErrorLiveData.value = false
        transactionloadingLiveData.value = true

        launch {
            val db = buildDb(getApplication())
            transactionLiveData.value = db.transactionDao().select(userId)
        }
    }

    fun refreshByTransactionId(transactionId: Int) {
        transactionForReviewLoadErrorLiveData.value = false
        transactionForReviewloadingLiveData.value = true

        launch {
            val db = buildDb(getApplication())
            transactionForReviewLiveData.value = db.transactionDao().selectTransactionWithRestaurant(transactionId)
        }
    }
}
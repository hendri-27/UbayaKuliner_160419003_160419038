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
import com.ubaya.ubayakuliner_160419003_160419038.model.DetailTransaction
import com.ubaya.ubayakuliner_160419003_160419038.model.DetailTransactionWithFood
import com.ubaya.ubayakuliner_160419003_160419038.model.Transaction
import com.ubaya.ubayakuliner_160419003_160419038.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTransactionViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val detailTransactionLiveData = MutableLiveData<ArrayList<DetailTransactionWithFood>>()
    val detailTransactionLoadErrorLiveData = MutableLiveData<Boolean>()
    val detailTransactionLoadingLiveData = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun fetch(transactionId:Int) {
        detailTransactionLoadErrorLiveData.value = false
        detailTransactionLoadingLiveData.value = true
        launch {
            val db = buildDb(getApplication())
            detailTransactionLiveData.value = ArrayList(db.detailTransactionDao().select(transactionId))
        }
    }
}
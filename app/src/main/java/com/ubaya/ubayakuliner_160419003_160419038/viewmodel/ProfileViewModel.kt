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
import com.ubaya.ubayakuliner_160419003_160419038.model.User
import com.ubaya.ubayakuliner_160419003_160419038.util.buildDb
import com.ubaya.ubayakuliner_160419003_160419038.util.userId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val profileLiveData = MutableLiveData<User>()
    val profileLoadErrorLiveData = MutableLiveData<Boolean>()
    val profileLoadingLiveData = MutableLiveData<Boolean>()
    private var job = Job()
    override val coroutineContext: CoroutineContext get() = job + Dispatchers.Main

    fun fetch(){
        profileLoadErrorLiveData.value = false
        profileLoadingLiveData.value = true

        launch {
            val db = buildDb(getApplication())
            profileLiveData.value = db.userDao().select(userId)
        }
    }

    fun update(name:String,gender:String,birthDate:String,phoneNumber:String,email:String,password:String){
        profileLoadErrorLiveData.value = false
        profileLoadingLiveData.value = true

        launch {
            val db = buildDb(getApplication())

            db.userDao().update(userId,name,gender,birthDate,phoneNumber,email,password)

            profileLiveData.value = db.userDao().select(userId)
        }
    }
}
package com.ubaya.ubayakuliner_160419003_160419038.model

import com.google.gson.annotations.SerializedName

data class DetailTransaction(
    val id:Int,
    var food:Food,
    @SerializedName("qty")
    var historyQty:Int,
    @SerializedName("price")
    var historyPrice:Int
)

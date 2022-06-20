package com.ubaya.ubayakuliner_160419003_160419038.model

data class Review(
    val id:Int,
    var user:User,
    var restaurantId:Int,
    var transactionId:String,
    var rating:Float,
    var message:String,
    var date:String
)

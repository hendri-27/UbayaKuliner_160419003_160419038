package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.Query

interface RestaurantDao {
    @Query("SELECT * FROM restaurant")
    suspend fun selectAll():List<Restaurant>

    @Query("SELECT * FROM restaurant WHERE id= :id")
    suspend fun select(id:Int):Restaurant

    @Query("UPDATE restaurant SET name = :name, address = :address, phone_number = :phoneNumber, photo_url = :photoUrl, rating_total = :ratingTotal WHERE id = :id")
    suspend fun update(id:Int,name:String,address:String,phoneNumber:String, photoUrl:String, ratingTotal:Float?)
}
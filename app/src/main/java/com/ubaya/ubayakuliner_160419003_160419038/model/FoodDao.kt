package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.Query

interface FoodDao {
    @Query("SELECT * FROM food")
    suspend fun selectAll():List<Food>

    @Query("SELECT * FROM food WHERE id= :id")
    suspend fun select(id:Int):Food

    @Query("UPDATE food SET name = :name, price = :price, photo_url = :photo_url WHERE id = :id")
    suspend fun update(id:Int,name:String,stock:Int,price:Int, photo_url:String)
}
package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.Dao
import androidx.room.Query

@Dao
interface FoodDao {
    @Query("SELECT * FROM food WHERE restaurant_id = :restoId")
    suspend fun selectAll(restoId: Int):ArrayList<Food>

    @Query("SELECT * FROM food WHERE id= :id")
    suspend fun select(id:Int):Food

    @Query("SELECT food.*, cart.* FROM food LEFT JOIN cart ON food.id = cart.food_id WHERE user_id = :userId AND restaurant_id = :restoId ORDER BY food.id")
    suspend fun select(userId:Int, restoId:Int):ArrayList<FoodWithCart>
}
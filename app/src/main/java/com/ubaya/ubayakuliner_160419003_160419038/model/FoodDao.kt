package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.Dao
import androidx.room.Query

@Dao
interface FoodDao {
    @Query("SELECT * FROM food WHERE id= :id")
    suspend fun select(id:Int):Food

    @Query("SELECT food.id AS food_id, food.restaurant_id AS food_restaurant_id, food.name AS food_name, food.price AS food_price, food.photo_url AS food_photo_url, cart.user_id AS cart_user_id, cart.food_id AS cart_food_id, cart.quantity as cart_quantity FROM food LEFT JOIN cart ON food.id = cart.food_id WHERE user_id = :userId AND restaurant_id = :restoId ORDER BY food.id")
    suspend fun select(userId:Int, restoId:Int):List<FoodWithCart>
}
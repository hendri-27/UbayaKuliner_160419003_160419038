package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(food: Food)

    @Query("SELECT * FROM food WHERE id= :id")
    suspend fun select(id:Int):Food

    @Query("SELECT food.id AS food_id, food.restaurant_id AS food_restaurant_id, food.name AS food_name, food.price AS food_price, food.photo_url AS food_photo_url, usercart.user_id AS cart_user_id, usercart.food_id AS cart_food_id, usercart.quantity as cart_quantity FROM food LEFT JOIN (SELECT  * FROM cart WHERE cart.user_id = :userId) AS usercart ON food.id = usercart.food_id WHERE restaurant_id = :restoId ORDER BY food.id")
    suspend fun select(userId:Int, restoId:Int):List<FoodWithCart>
}
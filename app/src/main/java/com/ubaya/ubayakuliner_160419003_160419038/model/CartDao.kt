package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.*

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cart: Cart)

    @Query(
        "SELECT food.id AS food_id, food.restaurant_id AS food_restaurant_id, food.name AS food_name, food.price AS food_price, food.photo_url AS food_photo_url, cart.user_id AS cart_user_id, cart.food_id AS cart_food_id, cart.quantity as cart_quantity FROM cart INNER JOIN food ON cart.food_id = food.id " +
            "WHERE cart_user_id= :user_id")
    suspend fun select(user_id: Int): List<CartWithFood>

    @Query("UPDATE cart set quantity = :quantity WHERE user_id = :user_id AND food_id = :food_id")
    suspend fun update(user_id: Int, food_id: Int, quantity: Int)

    @Delete
    suspend fun delete(cart: Cart)
}
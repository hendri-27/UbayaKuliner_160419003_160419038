package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.*

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg cart: Cart)

    @Query("SELECT food.id, food.restaurant_id, food.name, food.price, food.photo_url, " +
            "cart.quantity FROM cart INNER JOIN food ON cart.food_id = food.id WHERE user_id= :user_id")
    suspend fun select(user_id: Int): List<CartWithFood>

    @Query("UPDATE cart set quantity = :quantity WHERE user_id = :user_id AND food_id = :food_id")
    suspend fun update(user_id: Int, food_id: Int, quantity: Int)

    @Delete
    suspend fun delete(cart: Cart)
}
package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: Transaction)

    @Query("SELECT `transaction`.id AS transaction_id, `transaction`.user_id AS transaction_user_id, `transaction`.restaurant_id AS transaction_restaurant_id, `transaction`.date AS transaction_date, `transaction`.location AS transaction_location, `transaction`.delivery_fee AS transaction_delivery_fee, `transaction`.service_fee AS transaction_service_fee, `transaction`.subtotal AS transaction_subtotal, `transaction`.grandtotal AS transaction_grandtotal, `transaction`.status AS transaction_status, `transaction`.rate AS transaction_rate, restaurant.id AS restaurant_id, restaurant.name AS restaurant_name, restaurant.address AS restaurant_address, restaurant.phone_number AS restaurant_phone_number, restaurant.photo_url AS restaurant_photo_url, restaurant.rating_total AS restaurant_rating_total FROM `transaction` INNER JOIN restaurant ON " +
            "`transaction`.restaurant_id = restaurant.id WHERE `transaction`.user_id = :user_id")
    suspend fun select(user_id: Int): List<TransactionWithRestaurant>

    @Query("SELECT `transaction`.id AS transaction_id, `transaction`.user_id AS transaction_user_id, `transaction`.restaurant_id AS transaction_restaurant_id, `transaction`.date AS transaction_date, `transaction`.location AS transaction_location, `transaction`.delivery_fee AS transaction_delivery_fee, `transaction`.service_fee AS transaction_service_fee, `transaction`.subtotal AS transaction_subtotal, `transaction`.grandtotal AS transaction_grandtotal, `transaction`.status AS transaction_status, `transaction`.rate AS transaction_rate,  restaurant.id AS restaurant_id, restaurant.name AS restaurant_name, restaurant.address AS restaurant_address, restaurant.phone_number AS restaurant_phone_number, restaurant.photo_url AS restaurant_photo_url, restaurant.rating_total AS restaurant_rating_total FROM `transaction` INNER JOIN restaurant ON " +
            "`transaction`.restaurant_id = restaurant.id WHERE `transaction`.id = :transaction_id")
    suspend fun selectTransactionWithRestaurant(transaction_id: String): TransactionWithRestaurant
}
package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DetailTransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg detailTransaction: DetailTransaction)

    @Query("SELECT food.id AS food_id, food.restaurant_id AS food_restaurant_id, food.name AS food_name, food.price AS food_price, food.photo_url AS food_photo_url, `transaction`.id AS transaction_id, `transaction`.user_id AS transaction_user_id, `transaction`.restaurant_id AS transaction_restaurant_id, `transaction`.date AS transaction_date, `transaction`.location AS transaction_location, `transaction`.delivery_fee AS transaction_delivery_fee, `transaction`.service_fee AS transaction_service_fee, `transaction`.subtotal AS transaction_subtotal, `transaction`.grandtotal AS transaction_grandtotal, `transaction`.status AS transaction_status, `transaction`.rate AS transaction_rate, detail_transaction.quantity AS detail_quantity, detail_transaction.price AS detail_price FROM detail_transaction " +
            "INNER JOIN food ON detail_transaction.food_id = food.id INNER JOIN `transaction` ON " +
            "detail_transaction.transaction_id = `transaction`.id WHERE " +
            "detail_transaction.transaction_id= :transaction_id")
    suspend fun select(transaction_id: String): List<DetailTransactionWithFood>
}
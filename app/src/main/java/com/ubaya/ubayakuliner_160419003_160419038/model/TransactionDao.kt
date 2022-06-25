package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: Transaction)

    @Query("SELECT `transaction`.*, restaurant.* FROM `transaction` INNER JOIN restaurant ON " +
            "`transaction`.restaurant_id = restaurant.id WHERE `transaction`.user_id = :user_id")
    suspend fun select(user_id: Int): List<TransactionWithRestaurant>

    @Query("SELECT `transaction`.*, restaurant.* FROM `transaction` INNER JOIN restaurant ON " +
            "`transaction`.restaurant_id = restaurant.id WHERE `transaction`.id = :transaction_id")
    suspend fun selectTransactionWithRestaurant(transaction_id: Int): TransactionWithRestaurant
}
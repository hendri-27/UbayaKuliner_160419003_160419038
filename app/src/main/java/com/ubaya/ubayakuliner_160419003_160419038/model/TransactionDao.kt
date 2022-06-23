package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg transaction: Transaction)

    @Query("SELECT * FROM `transaction` WHERE user_id = :user_id")
    suspend fun select(user_id: Int): List<Transaction>

    @Query("SELECT * FROM `transaction` WHERE id = :transaction_id")
    suspend fun selectByTransaction(transaction_id: Int): Transaction

    @Query("SELECT `transaction`.*, restaurant.* FROM `transaction` INNER JOIN restaurant ON " +
            "`transaction`.restaurant_id = restaurant.id WHERE `transaction`.id = :transaction_id")
    suspend fun selectTransactionWithRestaurant(transaction_id: Int): TransactionWithRestaurant
}
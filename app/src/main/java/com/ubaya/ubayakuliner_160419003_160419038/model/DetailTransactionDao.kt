package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DetailTransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg detailTransaction: DetailTransaction)

    @Query("SELECT food.*, `transaction`.*, detail_transaction.quantity, detail_transaction.price FROM detail_transaction " +
            "INNER JOIN food ON detail_transaction.food_id = food.id INNER JOIN `transaction` ON " +
            "detail_transaction.transaction_id = `transaction`.id WHERE " +
            "detail_transaction.transaction_id= :transaction_id")
    suspend fun select(transaction_id: Int): ArrayList<DetailTransactionWithFood>
}
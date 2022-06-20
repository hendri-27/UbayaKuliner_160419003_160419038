package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "detail_transaction",
    primaryKeys = ["transaction_id","food_id"],
    foreignKeys = [
        ForeignKey(
            entity = Transaction::class,
            parentColumns = ["id"],
            childColumns = ["transaction_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Food::class,
            parentColumns = ["id"],
            childColumns = ["food_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DetailTransaction(
    @ColumnInfo(name = "transaction_id")
    val transactionId:Int,
    @ColumnInfo(name = "food_id")
    val foodId:Int,
    @ColumnInfo(name = "quantity")
    var qty:Int,
    @ColumnInfo(name = "price")
    var price:Int
)

data class DetailTransactionWithFood(
    @Embedded
    val transaction:Transaction,
    @Embedded
    val food:Food,
    val qty:Int,
    val price:Int
)
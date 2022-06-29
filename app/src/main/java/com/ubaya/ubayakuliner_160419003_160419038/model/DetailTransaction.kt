package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.*

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
    val transactionId:String,
    @ColumnInfo(name = "food_id")
    val foodId:Int,
    @ColumnInfo(name = "quantity")
    var qty:Int,
    @ColumnInfo(name = "price")
    var price:Int
)

data class DetailTransactionWithFood(
    @Embedded(prefix = "food_")
    val food:Food,
    @Embedded(prefix = "transaction_")
    val transaction: Transaction,
    @ColumnInfo(name = "detail_quantity")
    val qty:Int,
    @ColumnInfo(name = "detail_price")
    val detail_price:Int
)
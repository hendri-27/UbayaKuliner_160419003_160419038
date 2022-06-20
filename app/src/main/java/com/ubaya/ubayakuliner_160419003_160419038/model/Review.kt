package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.*

@Entity(primaryKeys = ["id","restaurant_id", "transaction_id"],
    foreignKeys = [
        ForeignKey(
            entity = Restaurant::class,
            parentColumns = ["id"],
            childColumns = ["restaurant_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Transaction::class,
            parentColumns = ["id"],
            childColumns = ["transaction_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Review(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name = "restaurant_id")
    val restaurantId:Int,
    @ColumnInfo(name = "transaction_id")
    val transactionId:Int,
    var rating:Float,
    var message:String,
    var date:String
)

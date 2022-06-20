package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.*

@Entity
data class Review(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @Relation(
        parentColumn = "restaurantId",
        entityColumn = "transactionId",
        associateBy = Junction(RestaurantTransactionCrossRef::class)
    )
    @ColumnInfo(name = "rating")
    var rating:Float,
    @ColumnInfo(name = "message")
    var message:String,
    @ColumnInfo(name = "date")
    var date:String
)

package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "detail_transaction")
data class DetailTransaction(
    @Embedded
    var restaurant: Restaurant,
    @Relation(
        parentColumn = "restaurantId",
        entityColumn = "foodId",
        associateBy = Junction(RestaurantFoodCrossRef::class)
    )
    @Embedded
    var food:Food,
    @ColumnInfo(name = "quantity")
    var qty:Int,
    @ColumnInfo(name = "price")
    var price:Int
)

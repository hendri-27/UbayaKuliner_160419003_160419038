package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.*

@Entity(primaryKeys = ["user_id", "food_id"], foreignKeys = [
    ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["user_id"],
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = Food::class,
        parentColumns = ["id"],
        childColumns = ["food_id"],
        onDelete = ForeignKey.CASCADE
    )
])
data class Cart (
    @ColumnInfo(name = "user_id")
    val userId:Int,
    @ColumnInfo(name = "food_id")
    val foodId:Int,
    @ColumnInfo(name = "quantity")
    var qty:Int
)

data class CartWithFood(
    @Embedded(prefix="food_")
    var food:Food,
    @Embedded(prefix="cart_")
    var cart:Cart
)



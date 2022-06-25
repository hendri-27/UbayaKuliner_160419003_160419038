package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.*


@Entity(
    foreignKeys = [ForeignKey(
        entity = Restaurant::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("restaurant_id"),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class Food(
    @ColumnInfo(name = "restaurant_id")
    val restaurantId:Int,
    @ColumnInfo(name = "name")
    var name:String,
    @ColumnInfo(name = "price")
    var price:Int,
    @ColumnInfo(name = "photo_url")
    var photoURL:String?,
    @PrimaryKey(autoGenerate = true)
    val food_id:Int = 0
)

data class FoodWithCart(
    @Embedded
    var food: Food,
    @Embedded
    var cart: Cart?
)

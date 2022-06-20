package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


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
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name = "restaurant_id")
    val restaurantId:String,
    @ColumnInfo(name = "name")
    var name:String,
    @ColumnInfo(name = "stock")
    var stock:Int,
    @ColumnInfo(name = "price")
    var price:Int,
    @ColumnInfo(name = "photo_url")
    var photoURL:String?
)

package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.*

@Entity(primaryKeys = ["id","user_id", "restaurant_id"],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Restaurant::class,
            parentColumns = ["id"],
            childColumns = ["restaurant_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Transaction(
    @ColumnInfo(name = "user_id")
    val userId:Int,
    @ColumnInfo(name = "restaurant_id")
    val restaurantId:Int,
    var date:String,
    var location:String,
//    @SerializedName("payment_method")
//    var paymentMethod:String,
    @ColumnInfo(name = "delivery_fee")
    var deliveryFee:Int,
    @ColumnInfo(name = "service_fee")
    var serviceFee:Int,
    @ColumnInfo(name = "food_subtotal")
    var foodSubtotal:Int,
    @ColumnInfo(name = "grand_total")
    var grandTotal:Int,
    var status:String,
    var rate:Float?,
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0
)

data class TransactionWithRestaurant(
    @Embedded
    val transaction: Transaction,
    @Embedded
    val restaurant: Restaurant
)
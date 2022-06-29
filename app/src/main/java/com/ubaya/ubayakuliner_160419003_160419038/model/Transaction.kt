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
    ],
    indices = [Index(value = ["id"], unique = true)]
)
data class Transaction(
    @ColumnInfo(name = "user_id")
    val userId:Int,
    @ColumnInfo(name = "restaurant_id")
    val restaurantId:Int,
    var date:String,
    var location:String,
    @ColumnInfo(name="payment_method", defaultValue = "Cash")
    var paymentMethod:String,
    @ColumnInfo(name = "delivery_fee")
    var deliveryFee:Int,
    @ColumnInfo(name = "service_fee")
    var serviceFee:Int,
    @ColumnInfo(name = "subtotal")
    var subtotal:Int,
    @ColumnInfo(name = "grandtotal")
    var grandTotal:Int,
    var status:String,
    var rate:Float?,
    var id:String
)

data class TransactionWithRestaurant(
    @Embedded(prefix = "transaction_")
    val transaction: Transaction,
    @Embedded(prefix = "restaurant_")
    val restaurant: Restaurant
)
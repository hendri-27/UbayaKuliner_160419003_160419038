package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.*

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id:String,
    @Embedded
    var user:User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "restaurantId",
        associateBy = Junction(RestaurantUserCrossRef::class)
    )
    @Embedded
    var restaurant:Restaurant,
    @ColumnInfo(name = "date")
    var date:String,
    @ColumnInfo(name = "location")
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
    @ColumnInfo(name = "status")
    var status:String,
    @ColumnInfo(name = "rate")
    var rate:String?
)

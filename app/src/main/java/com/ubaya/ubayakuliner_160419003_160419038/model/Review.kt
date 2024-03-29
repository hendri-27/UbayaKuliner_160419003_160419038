package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.*

@Entity(primaryKeys = ["restaurant_id", "transaction_id"],
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
        ),
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Review(
    @ColumnInfo(name = "restaurant_id")
    val restaurantId:Int,
    @ColumnInfo(name = "transaction_id")
    val transactionId:String,
    @ColumnInfo(name = "user_id")
    val userId:Int,
    var rating:Float,
    var message:String,
    var date:String,
)

data class ReviewWithUser(
    @Embedded
    val review:Review,
    @Embedded
    val user:User
)

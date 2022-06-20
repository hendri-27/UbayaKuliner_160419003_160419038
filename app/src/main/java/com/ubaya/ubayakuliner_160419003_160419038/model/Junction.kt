package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "restaurantId"])
data class RestaurantUserCrossRef(
    val userId: Int,
    val restaurantId: Int
)

@Entity(primaryKeys = ["restaurantId", "transactionId"])
data class RestaurantTransactionCrossRef(
    val restaurantId: Int,
    val transactionId: Int
)

@Entity(primaryKeys = ["restaurantId", "foodId"])
data class RestaurantFoodCrossRef(
    val restaurantId: Int,
    val foodId: Int
)

@Entity(primaryKeys = ["userId", "foodId"])
data class UserFoodCrossRef(
    val userId: Int,
    val foodId: Int
)
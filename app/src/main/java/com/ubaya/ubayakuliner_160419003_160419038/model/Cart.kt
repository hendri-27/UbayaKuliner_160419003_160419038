package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.*

@Entity
data class Cart (
    @Embedded
    val user:User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "foodId",
        associateBy = Junction(UserFoodCrossRef::class)
    )
    @Embedded
    val food:Food,
    @ColumnInfo(name = "quantity")
    var qty:Int
)
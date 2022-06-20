package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.*

@Entity
data class Cart (
    @Relation(
        parentColumn = "userId",
        entityColumn = "id"
    )
    val user:User,
    @Relation(
        parentColumn = "foodId",
        entityColumn = "id"
    )
    val food:Food,
    @ColumnInfo(name = "qty")
    var cartQty:Int
)
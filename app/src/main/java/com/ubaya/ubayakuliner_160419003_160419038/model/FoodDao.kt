package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.Dao
import androidx.room.Query

@Dao
interface FoodDao {
    @Query("SELECT * FROM food WHERE restaurant_id = :restoId")
    suspend fun selectAll(restoId: Int):List<Food>

    @Query("SELECT * FROM food WHERE id= :id")
    suspend fun select(id:Int):Food
}
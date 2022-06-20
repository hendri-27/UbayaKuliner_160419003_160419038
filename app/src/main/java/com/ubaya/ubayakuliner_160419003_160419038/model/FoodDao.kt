package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg food:Food)
}
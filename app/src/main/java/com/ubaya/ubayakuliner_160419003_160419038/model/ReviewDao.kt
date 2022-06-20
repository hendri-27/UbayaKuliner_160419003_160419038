package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg review:Review)

    @Query("SELECT * FROM review where restaurant_id = :idResto")
    suspend fun select(idResto:Int):List<Review>
}
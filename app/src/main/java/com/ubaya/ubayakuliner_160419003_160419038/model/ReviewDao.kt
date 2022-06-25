package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(review:Review)

    @Query("SELECT review.*, user.* FROM review INNER JOIN user ON review.user_id = user.id WHERE review.restaurant_id = :restoId")
    suspend fun select(restoId:Int):List<ReviewWithUser>
}
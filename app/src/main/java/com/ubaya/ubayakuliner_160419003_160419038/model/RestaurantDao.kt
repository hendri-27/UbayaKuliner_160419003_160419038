package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RestaurantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(restaurant: Restaurant)

    @Query("SELECT * FROM restaurant")
    suspend fun selectAll():List<Restaurant>

    @Query("SELECT * FROM restaurant WHERE id= :id")
    suspend fun select(id:Int):Restaurant

    @Query("UPDATE restaurant SET rating_total = (coalesce(rating_total,:ratingTotal) + :ratingTotal)/2 WHERE id = :id")
    suspend fun update(id:Int,ratingTotal:Float)
}
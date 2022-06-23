package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Restaurant(
    @ColumnInfo(name = "name")
    var name:String,
    @ColumnInfo(name = "address")
    var address:String,
    @ColumnInfo(name = "phone_number")
    var phoneNumber:String,
    @ColumnInfo(name = "photo_url")
    var photoURL:String?,
    @ColumnInfo(name = "rating_total")
    var ratingTotal:Float?,
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0
)
package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @ColumnInfo(name = "username")
    var username:String,
    @ColumnInfo(name = "name")
    var name:String,
    @ColumnInfo(name = "gender")
    var gender:String,
    @ColumnInfo(name = "birth_date")
    var birthDate:String,
    @ColumnInfo(name = "phone_number")
    var phoneNumber:String,
    @ColumnInfo(name = "email")
    var email:String,
    @ColumnInfo(name = "password")
    var password:String,
    @ColumnInfo(name = "photo_url")
    var photoURL:String?,
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0
)
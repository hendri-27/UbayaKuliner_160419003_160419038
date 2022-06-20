package com.ubaya.ubayakuliner_160419003_160419038.model

import androidx.room.Query

interface UserDao {
    @Query("SELECT * FROM user WHERE id= :id")
    suspend fun select(id:Int):User

    @Query("UPDATE user SET name = :name, gender = :gender, birth_date = :birthDate, phone_number = :phoneNumber, email = :email, password = :password WHERE id = :id")
    suspend fun update(id:Int,name:String,gender:String,birthDate:String,phoneNumber:String, email:String, password:String)
}
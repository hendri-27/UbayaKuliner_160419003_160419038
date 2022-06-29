package com.ubaya.ubayakuliner_160419003_160419038.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubaya.ubayakuliner_160419003_160419038.util.*

@Database(entities = [Cart::class, DetailTransaction::class, Food::class, Restaurant::class,
Review::class, Transaction::class, User::class], version = 2)
abstract class UbayaKulinerDatabase: RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun detailTransactionDao(): DetailTransactionDao
    abstract fun foodDao(): FoodDao
    abstract fun restaurantDao(): RestaurantDao
    abstract fun reviewDao(): ReviewDao
    abstract fun transactionDao(): TransactionDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var instance: UbayaKulinerDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                UbayaKulinerDatabase::class.java,
                "ubayakulinerdb")
                .addMigrations(MIGRATION_1_2)
                .build()

        operator fun invoke(context: Context) {
            if(instance != null) {
                synchronized(LOCK) { // Jangan sampai ada thread lain yang menjalankan ini
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}
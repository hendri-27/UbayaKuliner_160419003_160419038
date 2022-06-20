package com.ubaya.ubayakuliner_160419003_160419038.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.room.Room
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.ubayakuliner_160419003_160419038.R
import com.ubaya.ubayakuliner_160419003_160419038.model.UbayaKulinerDatabase
import java.lang.Exception

val DB_NAME = "ubayakulinerdb"

fun buildDb(context: Context) =
    Room.databaseBuilder(context, UbayaKulinerDatabase::class.java, DB_NAME)
//        .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
        .build()

fun ImageView.loadImage(url:String?, progressBar: ProgressBar){
    Picasso.get()
        .load(url)
        .resize(400,400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object : Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {

            }
        })
}

const val userId = 1
val arrGender:Array<String> = arrayOf("Male","Female")
val arrPaymentMethod:Array<String> = arrayOf("Cash")
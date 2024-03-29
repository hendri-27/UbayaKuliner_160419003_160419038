package com.ubaya.ubayakuliner_160419003_160419038.util

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.ubayakuliner_160419003_160419038.R
import com.ubaya.ubayakuliner_160419003_160419038.model.UbayaKulinerDatabase

val DB_NAME = "ubayakulinerdb"
const val userId = 1

val arrGender:Array<String> = arrayOf("Male","Female")
val arrPaymentMethod:Array<String> = arrayOf("Cash")

fun buildDb(context: Context) =
    Room.databaseBuilder(context, UbayaKulinerDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2)
        .build()

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE `transaction` ADD COLUMN payment_method TEXT DEFAULT 'Cash' NOT NULL")
    }
}

@BindingAdapter("android:imageUrl", "android:progressBar")
fun loadPhotoURL(view: ImageView, imageUrl: String?, pb: ProgressBar) {
    imageUrl?.let {
        view.loadImage(it, pb)
    }
}

@BindingAdapter("android:qtyCart")
fun tintButton(v:ImageView,qtyCart:Int) {
    if (qtyCart == 199){
        v.setColorFilter(
            ContextCompat.getColor(v.context, android.R.color.darker_gray),
            PorterDuff.Mode.MULTIPLY)
    }else{
        v.setColorFilter(
                Color.parseColor("#DC5959"),
                PorterDuff.Mode.MULTIPLY)
    }
}

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
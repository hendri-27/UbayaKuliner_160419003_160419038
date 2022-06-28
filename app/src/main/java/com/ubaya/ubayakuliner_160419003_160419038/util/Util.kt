package com.ubaya.ubayakuliner_160419003_160419038.util

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.support.annotation.ColorInt
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.room.Room
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.ubayakuliner_160419003_160419038.R
import com.ubaya.ubayakuliner_160419003_160419038.model.UbayaKulinerDatabase
import kotlinx.android.synthetic.main.fragment_profile.*
import java.text.SimpleDateFormat
import java.util.*

val DB_NAME = "ubayakulinerdb"
const val userId = 1

//enum class Gender {
//    Male,       //0
//    Female      //1
//}
//
//enum class PaymentMethods {
//    Cash,       //0
//}

val arrGender:Array<String> = arrayOf("Male","Female")
val arrPaymentMethod:Array<String> = arrayOf("Cash")

fun buildDb(context: Context) =
    Room.databaseBuilder(context, UbayaKulinerDatabase::class.java, DB_NAME)
//        .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
        .build()

//val MIGRATION_1_2 = object : Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL("ALTER TABLE `transaction` ADD COLUMN payment_method STRING DEFAULT 'Cash' NOT NULL")
//    }
//}

@BindingAdapter("android:imageUrl", "android:progressBar")
fun loadPhotoURL(view: ImageView, imageUrl: String?, pb: ProgressBar) {
    imageUrl?.let {
        view.loadImage(it, pb)
    }
}

@BindingAdapter("android:birthDate")
fun updateDate(view: EditText, birthDate:String?) {
    birthDate?.let{
        val myFormat = "dd-MM-yyyy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        var myCalendar: Calendar = Calendar.getInstance()

        val bod:List<String> = it.split("-")
        myCalendar[Calendar.YEAR] = Integer.parseInt(bod[2])
        myCalendar[Calendar.MONTH] = Integer.parseInt(bod[1])-1
        myCalendar[Calendar.DAY_OF_MONTH] = Integer.parseInt(bod[0])

        view.setText(dateFormat.format(myCalendar.time))
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

//@BindingAdapter("android:setGender")
//fun setGender(view: Spinner, gender: Gender) {
//    view.setSelection(gender.ordinal)
//}
//
//@InverseBindingAdapter(attribute = "android:setGender")
//fun getGender(view: Spinner): Gender {
//    return Gender.values()[view.selectedItemPosition]
//}
//
//@BindingAdapter("android:setGenderAttrChanged")
//fun setGenderListener(view: Spinner, listener: InverseBindingListener) {
//    if (view.onItemSelectedListener == null) {
//        view.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                listener.onChange()
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//            }
//        }
//    }
//}
//
//@BindingAdapter("android:setPayment")
//fun setPayment(view: Spinner, payment: PaymentMethods) {
//    view.setSelection(payment.ordinal)
//}
//
//@InverseBindingAdapter(attribute = "android:setPayment")
//fun getPayment(view: Spinner): String {
//    return PaymentMethods.values()[view.selectedItemPosition].toString()
//}
//
//@BindingAdapter("android:setPaymentAttrChanged")
//fun setPaymentListener(view: Spinner, listener: InverseBindingListener) {
//    if (view.onItemSelectedListener == null) {
//        view.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                listener.onChange()
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//            }
//        }
//    }
//}

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
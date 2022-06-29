package com.ubaya.ubayakuliner_160419003_160419038.view

import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.textfield.TextInputEditText
import com.ubaya.ubayakuliner_160419003_160419038.model.CartWithFood
import com.ubaya.ubayakuliner_160419003_160419038.model.FoodWithCart
import com.ubaya.ubayakuliner_160419003_160419038.model.Transaction

interface ButtonRateClickListener {
    fun onButtonRateClick(v:View,trans:Transaction)
}

interface ButtonCompleteClickListener {
    fun onButtonCompleteClick(v: View,transId: String)
}

interface TransactionCardClickListener{
    fun onCardClick(v:View, restoName:String, transId: String)
}

interface RestaurantCardClickListener{
    fun onCardClick(v:View, restoId:Int)
}

interface ButtonProfileCLickListener{
    fun onButtonSaveChangeClick(v:View)
}

interface CalendarClickListener{
    fun onCalendarClick(v:View)
}

interface ButtonIncreaseFICListener {
    fun onButtonIncreaseCLick(v: View, obj: CartWithFood)
}

interface ButtonDecreaseFICListener {
    fun onButtonDecreaseCLick(v: View, btnIncrease: ImageView, quantity: TextView, obj: CartWithFood)
}

//interface SpinnerGenderClickListener{
//    fun onSpinnerClick(parent:AdapterView<*>, v:View,position:Int,id:Int)
//}

interface ButtonAddCartListener {
    fun onButtonAddCartCLick(v: View, cardQty: CardView, obj: FoodWithCart)
}

interface ButtonIncreaseFIRListener {
    fun onButtonIncreaseCLick(v: View, btnIncrease: ImageView, obj: FoodWithCart)
}

interface ButtonDecreaseFIRListener {
    fun onButtonDecreaseCLick(v: View, btnIncrease: ImageView, btnAddCart: Button, cardQty: CardView, obj: FoodWithCart)
}

interface SubmitReviewListener {
    fun onButtonSubmitCLick(v: View)
}

interface CheckoutListener {
    fun onButtonCheckoutClick(v: View)
}

interface PlaceOrderListener {
    fun onButtonPlaceOrderClick(v: View, addressUser: TextInputEditText)
}
//
//interface SpinnerPaymentListener{
//    fun onSpinnerClick(parent:AdapterView<*>, v:View, position:Int, id:Int)
//}

interface ReviewRestaurantListener {
    fun onCardReviewClick(v: View)
}

interface OpenCartRestaurantListener {
    fun onButtonCartClick(v: View)
}

interface RatingBarListener{
    fun onRatingBarChange(v:View,rating:Float,btnSubmit:Button)
}
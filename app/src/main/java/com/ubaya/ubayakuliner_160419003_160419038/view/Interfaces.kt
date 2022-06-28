package com.ubaya.ubayakuliner_160419003_160419038.view

import android.view.View

interface ButttonRateClickListener {
    fun onButtonRateClick(v:View,transId:Int)
}

interface ButtonCompleteClickListener {
    fun onButtonCompleteClick(v: View,transId: Int)
}

interface TransactionCardClickListener{
    fun onCardClick(v:View, restoName:String, transId: Int)
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
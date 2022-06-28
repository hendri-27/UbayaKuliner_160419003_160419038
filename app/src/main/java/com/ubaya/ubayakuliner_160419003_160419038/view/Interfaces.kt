package com.ubaya.ubayakuliner_160419003_160419038.view

import android.view.View
import android.widget.AdapterView

interface ButttonRateClickListener {
    fun onButtonRateClick(v:View,transId:String)
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











interface SpinnerGenderClickListener{
    fun onSpinnerClick(parent:AdapterView<*>, v:View,position:Int,id:Int)
}


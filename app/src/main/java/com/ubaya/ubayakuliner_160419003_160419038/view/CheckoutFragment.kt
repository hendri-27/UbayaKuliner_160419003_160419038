package com.ubaya.ubayakuliner_160419003_160419038.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayakuliner_160419003_160419038.R
import com.ubaya.ubayakuliner_160419003_160419038.util.arrPaymentMethod
import com.ubaya.ubayakuliner_160419003_160419038.util.userId
import com.ubaya.ubayakuliner_160419003_160419038.viewmodel.CheckoutViewModel
import com.ubaya.ubayakuliner_160419003_160419038.viewmodel.ListCartViewModel
import kotlinx.android.synthetic.main.fragment_checkout.*
import kotlinx.android.synthetic.main.fragment_profile.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 * Use the [CheckoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CheckoutFragment : Fragment() {
    private lateinit var viewModelCheckout:CheckoutViewModel
    private lateinit var viewModelCart:ListCartViewModel
    private var subTotal:Int = 0
    private var serviceFee:Int = (0.1 * Random.nextInt(2000,10000)).toInt()
    private var deliveryFee:Int = (Random.nextDouble(0.1,1.0) * Random.nextInt(2000,10000)).toInt()
    private val listCartAdapter = CheckoutAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModelCheckout = ViewModelProvider(this).get(CheckoutViewModel::class.java)
        viewModelCart = ViewModelProvider(this).get(ListCartViewModel::class.java)

        val adapter = ArrayAdapter(view.context, R.layout.myspinner_layout, arrPaymentMethod)
        adapter.setDropDownViewResource(R.layout.myspinner_item_layout)
        spinnerPaymentMethod.adapter = adapter

        recViewCheckout.layoutManager = LinearLayoutManager(context)
        recViewCheckout.adapter = listCartAdapter


        listCartAdapter.updateListCheckout(ArrayList(listCart.toList()))

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModelCheckout.UserLiveData.observe(viewLifecycleOwner) {
            editCheckoutPhone.setText(it.phoneNumber)

            textDetailOrderSubtotal.text = String.format("Rp%,d", subTotal)
            textDetailOrderDeliveryFee.text = String.format("Rp%,d", deliveryFee)
            textDetailOrderServiceFee.text = String.format("Rp%,d", serviceFee)
            textDetailTransGrandtotal.text = String.format("Rp%,d", subTotal + deliveryFee + serviceFee)
            textOrderGrandtotal.text = String.format("Rp%,d", subTotal + deliveryFee + serviceFee)

        }
        viewModelCheckout.UserLoadErrorLiveData.observe(viewLifecycleOwner){
            textErrorCheckout.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModelCheckout.UserLoadingLiveData.observe(viewLifecycleOwner){
            if (it){
                scrollViewCheckout.visibility = View.GONE
                cardViewCheckout.visibility = View.GONE
                progressLoadCheckout.visibility = View.VISIBLE
            }else{
                scrollViewCheckout.visibility = View.VISIBLE
                cardViewCheckout.visibility = View.VISIBLE
                progressLoadCheckout.visibility = View.GONE
            }
        }

        viewModelCheckout.RestaurantLiveData.observe(viewLifecycleOwner) {
            textCheckoutRestoName.text = it.name
        }
        viewModelCheckout.RestaurantLoadErrorLiveData.observe(viewLifecycleOwner){
            textErrorCheckout.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModelCheckout.RestaurantLoadingLiveData.observe(viewLifecycleOwner){
            if (it){
                scrollViewCheckout.visibility = View.GONE
                cardViewCheckout.visibility = View.GONE
                progressLoadCheckout.visibility = View.VISIBLE
            }else{
                scrollViewCheckout.visibility = View.VISIBLE
                cardViewCheckout.visibility = View.VISIBLE
                progressLoadCheckout.visibility = View.GONE
            }
        }
    }
}
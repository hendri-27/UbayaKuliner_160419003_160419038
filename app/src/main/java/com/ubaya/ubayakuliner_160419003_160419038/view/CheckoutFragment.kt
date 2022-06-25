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
import com.ubaya.ubayakuliner_160419003_160419038.model.DetailTransaction
import com.ubaya.ubayakuliner_160419003_160419038.model.Transaction
import com.ubaya.ubayakuliner_160419003_160419038.util.arrPaymentMethod
import com.ubaya.ubayakuliner_160419003_160419038.util.userId
import com.ubaya.ubayakuliner_160419003_160419038.viewmodel.CheckoutViewModel
import com.ubaya.ubayakuliner_160419003_160419038.viewmodel.ListCartViewModel
import kotlinx.android.synthetic.main.fragment_checkout.*
import kotlinx.android.synthetic.main.fragment_profile.*
import java.text.SimpleDateFormat
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
    private var serviceFee:Int = (0.1 * Random.nextInt(2000,10000)).toInt()
    private var deliveryFee:Int = (Random.nextDouble(0.1,1.0) * Random.nextInt(2000,10000)).toInt()
    private val listCartAdapter = CheckoutAdapter(arrayListOf())
    private var restaurantId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        restaurantId = CheckoutFragmentArgs.fromBundle(requireArguments()).restaurantId

        viewModelCheckout = ViewModelProvider(this).get(CheckoutViewModel::class.java)
        viewModelCheckout.fetchUser()
        viewModelCheckout.fetchRestaurant(restaurantId)
        viewModelCart = ViewModelProvider(this).get(ListCartViewModel::class.java)

        val adapter = ArrayAdapter(view.context, R.layout.myspinner_layout, arrPaymentMethod)
        adapter.setDropDownViewResource(R.layout.myspinner_item_layout)
        spinnerPaymentMethod.adapter = adapter

        recViewCheckout.layoutManager = LinearLayoutManager(context)
        recViewCheckout.adapter = listCartAdapter


        observeViewModel()
    }

    private fun observeViewModel(){
        viewModelCheckout.UserLiveData.observe(viewLifecycleOwner) {
            editCheckoutPhone.setText(it.phoneNumber)
            editCheckoutRecipientName.setText(it.name)
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

        viewModelCart.cartLiveData.observe(viewLifecycleOwner) {
            listCartAdapter.updateListCheckout(it)

            val cartWithFood = it
            var subTotal = 0
            for(item in it) {
                subTotal += (item.food.price * item.cart.qty)
            }
            val grandTotal = subTotal + deliveryFee + serviceFee

            textDetailOrderSubtotal.text = String.format("Rp%,d", subTotal)
            textDetailOrderDeliveryFee.text = String.format("Rp%,d", deliveryFee)
            textDetailOrderServiceFee.text = String.format("Rp%,d", serviceFee)
            textDetailTransGrandtotal.text = String.format("Rp%,d", grandTotal)
            textOrderGrandtotal.text = String.format("Rp%,d", grandTotal)

            buttonPlaceOrder.setOnClickListener{
                val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm")
                val currentDate = sdf.format(Date())
                val id = UUID.randomUUID().toString().replace("-", "").uppercase()
                val transaction = Transaction(userId, restaurantId, currentDate, editDeliveryAddress.text.toString(),
                    deliveryFee, serviceFee, subTotal, grandTotal, "Ongoing", null, id)
                var detailTransaction: ArrayList<DetailTransaction> = arrayListOf()

                for(list in cartWithFood) {
                    detailTransaction.add(DetailTransaction(id, list.food.food_id, list.cart.qty, list.food.price))
                }

                viewModelCheckout.placeOrder(transaction, detailTransaction)
            }
        }
        viewModelCart.cartLoadErrorLiveData.observe(viewLifecycleOwner){
            textErrorCheckout.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModelCart.cartLoadingLiveData.observe(viewLifecycleOwner){
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
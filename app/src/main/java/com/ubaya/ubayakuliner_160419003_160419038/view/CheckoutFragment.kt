package com.ubaya.ubayakuliner_160419003_160419038.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.google.android.material.textfield.TextInputEditText
import com.ubaya.ubayakuliner_160419003_160419038.R
import com.ubaya.ubayakuliner_160419003_160419038.databinding.FragmentCheckoutBinding
import com.ubaya.ubayakuliner_160419003_160419038.model.CartWithFood
import com.ubaya.ubayakuliner_160419003_160419038.model.DetailTransaction
import com.ubaya.ubayakuliner_160419003_160419038.model.Transaction
import com.ubaya.ubayakuliner_160419003_160419038.util.UbayaKulinerWorker
import com.ubaya.ubayakuliner_160419003_160419038.util.arrPaymentMethod
import com.ubaya.ubayakuliner_160419003_160419038.util.userId
import com.ubaya.ubayakuliner_160419003_160419038.viewmodel.CheckoutViewModel
import com.ubaya.ubayakuliner_160419003_160419038.viewmodel.ListCartViewModel
import kotlinx.android.synthetic.main.fragment_checkout.*
import kotlinx.android.synthetic.main.fragment_profile.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 * Use the [CheckoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CheckoutFragment : Fragment(), PlaceOrderListener{
    private lateinit var viewModelCheckout:CheckoutViewModel
    private lateinit var viewModelCart:ListCartViewModel
    private var serviceFee:Int = (0.1 * Random.nextInt(2000,10000)).toInt()
    private var deliveryFee:Int = (Random.nextDouble(0.1,1.0) * Random.nextInt(2000,10000)).toInt()
    private val listCartAdapter = CheckoutAdapter(arrayListOf())
    var restaurantId = 0
    var cartWithFood = ArrayList<CartWithFood>()
    private lateinit var dataBinding: FragmentCheckoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentCheckoutBinding.inflate(inflater, container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        restaurantId = CheckoutFragmentArgs.fromBundle(requireArguments()).restaurantId

        dataBinding.placeOrderListener = this
//        dataBinding.spinnerListener = this

        viewModelCheckout = ViewModelProvider(this).get(CheckoutViewModel::class.java)
        viewModelCheckout.fetchUser()
        viewModelCheckout.fetchRestaurant(restaurantId)
        viewModelCart = ViewModelProvider(this).get(ListCartViewModel::class.java)
        viewModelCart.refresh()

        val adapter = ArrayAdapter(view.context, R.layout.myspinner_layout, arrPaymentMethod)
        adapter.setDropDownViewResource(R.layout.myspinner_item_layout)
        spinnerPaymentMethod.adapter = adapter

        recViewCheckout.layoutManager = LinearLayoutManager(context)
        recViewCheckout.adapter = listCartAdapter

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModelCheckout.userLiveData.observe(viewLifecycleOwner) {
            dataBinding.user = it
//            editCheckoutPhone.setText(it.phoneNumber)
//            editCheckoutRecipientName.setText(it.name)
        }
        viewModelCheckout.userLoadErrorLiveData.observe(viewLifecycleOwner){
            textErrorCheckout.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModelCheckout.userLoadingLiveData.observe(viewLifecycleOwner){
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
            cartWithFood = it

            var subTotal = 0
            for(item in it) {
                subTotal += (item.food.price * item.cart.qty)
            }
            val grandTotal = subTotal + deliveryFee + serviceFee

            dataBinding.transaction = Transaction(userId, restaurantId, "", "", deliveryFee, serviceFee, subTotal, grandTotal, "Ongoing", null,"")

//            textDetailOrderSubtotal.text = String.format("Rp%,d", subTotal)
//            textDetailOrderDeliveryFee.text = String.format("Rp%,d", deliveryFee)
//            textDetailOrderServiceFee.text = String.format("Rp%,d", serviceFee)
//            textDetailTransGrandtotal.text = String.format("Rp%,d", grandTotal)
//            textOrderGrandtotal.text = String.format("Rp%,d", grandTotal)

//            buttonPlaceOrder.setOnClickListener{
//                val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm")
//                val currentDate = sdf.format(Date())
//                val id = UUID.randomUUID().toString().replace("-", "").uppercase()
//                val transaction = Transaction(userId, restaurantId, currentDate, editDeliveryAddress.text.toString(),
//                    deliveryFee, serviceFee, subTotal, grandTotal, "Ongoing", null, id)
//                var detailTransaction: ArrayList<DetailTransaction> = arrayListOf()
//
//                for(list in cartWithFood) {
//                    detailTransaction.add(DetailTransaction(id, list.food.id, list.cart.qty, list.food.price))
//                }
//
//                viewModelCheckout.placeOrder(transaction, detailTransaction)
//            }
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

        viewModelCheckout.restaurantLiveData.observe(viewLifecycleOwner) {
            dataBinding.restaurant = it
//            textCheckoutRestoName.text = it.name
        }
        viewModelCheckout.restaurantLoadErrorLiveData.observe(viewLifecycleOwner){
            textErrorCheckout.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModelCheckout.restaurantLoadingLiveData.observe(viewLifecycleOwner){
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

    override fun onButtonPlaceOrderClick(v: View, addressUser: TextInputEditText) {
        if (dataBinding.transaction!!.location.isNotEmpty()) {
            val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm")
            val currentDate = sdf.format(Date())
            val id = UUID.randomUUID().toString().replace("-", "").uppercase()
            var detailTransaction: ArrayList<DetailTransaction> = arrayListOf()

            dataBinding.transaction!!.date = currentDate
            dataBinding.transaction!!.id = id

            for (list in cartWithFood) {
                detailTransaction.add(
                    DetailTransaction(
                        id,
                        list.food.id,
                        list.cart.qty,
                        list.food.price
                    )
                )
            }

            viewModelCheckout.placeOrder(dataBinding.transaction!!, detailTransaction)

            // For Notification
            val deliveryDuration = Random.nextInt(10, 15).toLong()

            Toast.makeText(v.context, "Your order is on the way", Toast.LENGTH_LONG).show()

            val workRequest = OneTimeWorkRequestBuilder<UbayaKulinerWorker>()
                .setInitialDelay(deliveryDuration, TimeUnit.SECONDS)
                .setInputData(
                    workDataOf(
                        "title" to "Your food is arrived!",
                        "message" to "Don't forget to wash your hand before eating. Enjoy your meal!"
                    )
                )
                .build()
            WorkManager.getInstance(requireContext()).enqueue(workRequest)

            val action = CheckoutFragmentDirections.actionListTransaction()
            Navigation.findNavController(v).navigate(action)
        }else{
            Toast.makeText(v.context, "Please fill your delivery address!", Toast.LENGTH_LONG).show()
        }
    }

//    override fun onSpinnerClick(parent: AdapterView<*>, v: View, position: Int, id: Int) {
//        parent.setSelection(position)
//    }
}
package com.ubaya.ubayakuliner_160419003_160419038.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayakuliner_160419003_160419038.R
import com.ubaya.ubayakuliner_160419003_160419038.databinding.FragmentCartBinding
import com.ubaya.ubayakuliner_160419003_160419038.util.userId
import com.ubaya.ubayakuliner_160419003_160419038.viewmodel.ListCartViewModel
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_cart.refreshLayout
import kotlinx.android.synthetic.main.fragment_detail_restaurant.*
import kotlinx.android.synthetic.main.fragment_list_restaurant.*

/**
 * A simple [Fragment] subclass.
 * Use the [CartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartFragment : Fragment(), CheckoutListener{
    private lateinit var viewModel:ListCartViewModel
    private val cartListAdapter = ListCartAdapter(arrayListOf(), this)
    private lateinit var dataBinding: FragmentCartBinding
    var restaurantId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentCartBinding.inflate(inflater, container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataBinding.checkoutListener = this

        viewModel = ViewModelProvider(this).get(ListCartViewModel::class.java)
        viewModel.refresh()

        recViewListCart.layoutManager = LinearLayoutManager(context)
        recViewListCart.adapter = cartListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recViewListCart.visibility = View.GONE
            cardViewCheckout.visibility = View.GONE
            textErrorCart.visibility = View.GONE
            progressLoadCart.visibility = View.GONE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel(){
        viewModel.cartLiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()){
                textNoDataListCart.visibility = View.GONE
                dataBinding.cardViewCheckout.visibility = View.VISIBLE
                cartListAdapter.updateListFood(it)

                var subTotal = 0
                for (cartWithFood in it){
                    subTotal += (cartWithFood.food.price * cartWithFood.cart.qty)
                }

                textCartSubtotal.text = String.format("Rp%,d", subTotal)
                cartListAdapter.setSubTotal(subTotal)

                restaurantId = it[0].food.restaurantId
//                buttonCartCheckout.setOnClickListener {
//                    val action = CartFragmentDirections.actionCheckoutFragment(restaurantId)
//                    Navigation.findNavController(it).navigate(action)
//                }
            }else {
                dataBinding.textNoDataListCart.visibility = View.VISIBLE
                dataBinding.cardViewCheckout.visibility = View.GONE
            }
        }
        viewModel.cartLoadErrorLiveData.observe(viewLifecycleOwner){
            textErrorCart.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.cartLoadingLiveData.observe(viewLifecycleOwner){
            if (it){ //Sedang Load
                recViewListCart.visibility = View.GONE
                cardViewCheckout.visibility = View.GONE
                progressLoadCart.visibility = View.VISIBLE
            }else{
                recViewListCart.visibility = View.VISIBLE
                cardViewCheckout.visibility = View.VISIBLE
                progressLoadCart.visibility = View.GONE
            }
        }
    }

    override fun onButtonCheckoutClick(v: View) {
        val action = CartFragmentDirections.actionCheckoutFragment(restaurantId)
        Navigation.findNavController(v).navigate(action)
    }
}
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
import com.ubaya.ubayakuliner_160419003_160419038.model.Cart
import com.ubaya.ubayakuliner_160419003_160419038.viewmodel.DetailRestaurantViewModel
import kotlinx.android.synthetic.main.fragment_detail_restaurant.*

/**
 * A simple [Fragment] subclass.
 * Use the [DetailRestaurantFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailRestaurantFragment : Fragment() {
    private lateinit var viewModel:DetailRestaurantViewModel
    private val cartWithFoodListAdapter = ListFoodAdapter(arrayListOf(), viewModel)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_restaurant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(DetailRestaurantViewModel::class.java)

//        cartWithFoodListAdapter.viewModel = viewModel
        val restaurantId = DetailRestaurantFragmentArgs.fromBundle(requireArguments()).restaurantId
        viewModel.fetchFoodWithCart(restaurantId)
        viewModel.fetchRestaurant(restaurantId)

        cardReview.setOnClickListener {
            val action = DetailRestaurantFragmentDirections.actionListReviewFragment(restaurantId)
            Navigation.findNavController(it).navigate(action)
        }
        buttonCart.setOnClickListener {
            val action = DetailRestaurantFragmentDirections.actionItemCart()
            Navigation.findNavController(it).navigate(action)
        }
        recFoodView.layoutManager = LinearLayoutManager(context)
        recFoodView.adapter = cartWithFoodListAdapter

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.foodLiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()){
                textNoDataDetailResto.visibility = View.GONE
                cartWithFoodListAdapter.updateListFoodWithCart(it)
            }else {
                textNoDataDetailResto.visibility = View.VISIBLE
            }
        }
        viewModel.foodLoadErrorLiveData.observe(viewLifecycleOwner){
            textErrorDetailResto.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.foodLoadingLiveData.observe(viewLifecycleOwner){
            if (it){ //Sedang Load
                scrollViewDetailResto.visibility = View.GONE
                buttonCart.visibility = View.GONE
                progressLoadDetailResto.visibility = View.VISIBLE
            }else{
                scrollViewDetailResto.visibility = View.VISIBLE
                buttonCart.visibility = View.VISIBLE
                progressLoadDetailResto.visibility = View.GONE
            }
        }

        viewModel.restaurantLiveData.observe(viewLifecycleOwner) {
            if (it != null){
                textNoDataDetailResto.visibility = View.GONE
                textDetailRestoName.text = it.name
                textDetailRestoAddress.text = it.address
                textDetailRestoPhone.text = it.phoneNumber
                ratingBarReview.rating = it.ratingTotal ?: 0.0f
                textDetailRestoRating.text = "${it.ratingTotal ?: "New"} (See Reviews)"
            }else {
                textNoDataDetailResto.visibility = View.VISIBLE
            }
        }
    }
}
package com.ubaya.ubayakuliner_160419003_160419038.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.ubayakuliner_160419003_160419038.R
import com.ubaya.ubayakuliner_160419003_160419038.model.Review
import com.ubaya.ubayakuliner_160419003_160419038.util.loadImage
import com.ubaya.ubayakuliner_160419003_160419038.viewmodel.ListReviewViewModel
import com.ubaya.ubayakuliner_160419003_160419038.viewmodel.ListTransactionViewModel
import kotlinx.android.synthetic.main.fragment_add_review.*
import kotlinx.android.synthetic.main.fragment_cart.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [AddReviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddReviewFragment : Fragment() {
    private lateinit var viewModelTransaction: ListTransactionViewModel
    private lateinit var viewModelReview: ListReviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val transactionId = AddReviewFragmentArgs.fromBundle(requireArguments()).transactionId

        viewModelTransaction = ViewModelProvider(this).get(ListTransactionViewModel::class.java)
        viewModelTransaction.refreshByTransactionId(transactionId)
        viewModelReview = ViewModelProvider(this).get(ListReviewViewModel::class.java)

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModelTransaction.transactionForReviewLiveData.observe(viewLifecycleOwner) {
            val transaction = it.transaction
            val restaurant = it.restaurant

            textReviewNameRestaurant.text = restaurant.name
            imageReviewRestoPhoto.loadImage(
                "https://hendri-27.github.io/ubayakuliner_db/images"+restaurant.photoURL,progressLoadingReviewRestoPhoto
            )

            ratingBarAddReview.setOnRatingBarChangeListener { ratingBar, fl, b ->
                buttonSubmit.isEnabled = true
            }

            buttonSubmit.setOnClickListener{
                val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm")
                val currentDate = sdf.format(Date())

                val review = Review(restaurant.id, transaction.id, transaction.userId, ratingBarAddReview.rating,
                editUserReview.text.toString(), currentDate)
                viewModelReview.insert(review)
            }
        }

        viewModelTransaction.transactionForReviewloadingLiveData.observe(viewLifecycleOwner){
            if (it){ //Sedang Load
                progressLoadingReviewRestoPhoto.visibility = View.VISIBLE
            }else{
                progressLoadingReviewRestoPhoto.visibility = View.GONE
            }
        }
    }
}
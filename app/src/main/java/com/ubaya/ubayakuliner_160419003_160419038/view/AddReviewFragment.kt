package com.ubaya.ubayakuliner_160419003_160419038.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.ubayakuliner_160419003_160419038.R
import com.ubaya.ubayakuliner_160419003_160419038.util.loadImage
import com.ubaya.ubayakuliner_160419003_160419038.viewmodel.ListReviewViewModel
import com.ubaya.ubayakuliner_160419003_160419038.viewmodel.ListTransactionViewModel
import kotlinx.android.synthetic.main.fragment_add_review.*
import kotlinx.android.synthetic.main.fragment_cart.*

/**
 * A simple [Fragment] subclass.
 * Use the [AddReviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddReviewFragment : Fragment() {
    private lateinit var viewModel: ListTransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val transactionId = AddReviewFragmentArgs.fromBundle(requireArguments()).transactionId

        viewModel = ViewModelProvider(this).get(ListTransactionViewModel::class.java)
        viewModel.refreshByTransactionId(transactionId)

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.transactionForReviewLiveData.observe(viewLifecycleOwner) {
            textReviewNameRestaurant.text = it.res.name
            imageReviewRestoPhoto.loadImage(
                "https://hendri-27.github.io/ubayakuliner_db/images"+transaction.restaurant.photoURL,progressLoadingReviewRestoPhoto
            )

            ratingBarAddReview.setOnRatingBarChangeListener { ratingBar, fl, b ->
                buttonSubmit.isEnabled = true
            }

            buttonSubmit.hasOnClickListeners{

            }
        }

        viewModel.transactionForReviewloadingLiveData.observe(viewLifecycleOwner){
            if (it){ //Sedang Load
                progressLoadingReviewRestoPhoto.visibility = View.VISIBLE
            }else{
                progressLoadingReviewRestoPhoto.visibility = View.GONE
            }
        }
    }
}
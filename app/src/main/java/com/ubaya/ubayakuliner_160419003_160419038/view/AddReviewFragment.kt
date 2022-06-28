package com.ubaya.ubayakuliner_160419003_160419038.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.ubayakuliner_160419003_160419038.R
import com.ubaya.ubayakuliner_160419003_160419038.databinding.FragmentAddReviewBinding
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
class AddReviewFragment : Fragment(), SubmitReviewListener {
    private lateinit var viewModelTransaction: ListTransactionViewModel
    private lateinit var viewModelReview: ListReviewViewModel
    private lateinit var dataBinding: FragmentAddReviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding =  FragmentAddReviewBinding.inflate(inflater, container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataBinding.submitListener = this
        val transactionId = AddReviewFragmentArgs.fromBundle(requireArguments()).transactionId

        viewModelTransaction = ViewModelProvider(this).get(ListTransactionViewModel::class.java)
        viewModelTransaction.refreshByTransactionId(transactionId)
        viewModelReview = ViewModelProvider(this).get(ListReviewViewModel::class.java)

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModelTransaction.transactionForReviewLiveData.observe(viewLifecycleOwner) {
            val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm")
            val currentDate = sdf.format(Date())

            dataBinding.transactionWithRestaurant = it
            dataBinding.review = Review(it.restaurant.id, it.transaction.id, it.transaction.userId, 0f, "", currentDate)

//            textReviewNameRestaurant.text = restaurant.name
//            imageReviewRestoPhoto.loadImage(
//                "https://hendri-27.github.io/ubayakuliner_db/images"+restaurant.photoURL,progressLoadingReviewRestoPhoto
//            )

//            ratingBarAddReview.setOnRatingBarChangeListener { ratingBar, fl, b ->
//                buttonSubmit.isEnabled = true
//            }

//            buttonSubmit.setOnClickListener{
//                viewModelReview.insert(dataBinding.review)
//            }
        }

        viewModelTransaction.transactionForReviewloadingLiveData.observe(viewLifecycleOwner){
            if (it){ //Sedang Load
                progressLoadingReviewRestoPhoto.visibility = View.VISIBLE
            }else{
                progressLoadingReviewRestoPhoto.visibility = View.GONE
            }
        }
    }

    override fun onButtonSubmitCLick(v: View) {
        viewModelReview.insert(dataBinding.review)
    }
}
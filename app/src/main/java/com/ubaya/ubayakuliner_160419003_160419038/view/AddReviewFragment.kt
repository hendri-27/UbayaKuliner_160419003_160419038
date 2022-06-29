package com.ubaya.ubayakuliner_160419003_160419038.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.ubayakuliner_160419003_160419038.databinding.FragmentAddReviewBinding
import com.ubaya.ubayakuliner_160419003_160419038.model.Review
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
class AddReviewFragment : Fragment(), SubmitReviewListener,RatingBarListener {
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
        dataBinding.ratingListener = this
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
        if (dataBinding.review!!.message.isNotEmpty()){
            viewModelReview.insert(dataBinding.review!!)

            Navigation.findNavController(v).popBackStack()
            Toast.makeText(v.context, "Success Add Review!", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(v.context, "Please fill the feedback!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onRatingBarChange(v: View, rating: Float, btnSubmit: Button) {
        btnSubmit.isEnabled = (rating != 0.0f)
    }
}
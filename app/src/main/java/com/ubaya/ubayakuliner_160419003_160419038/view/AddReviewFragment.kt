package com.ubaya.ubayakuliner_160419003_160419038.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ubaya.ubayakuliner_160419003_160419038.R
import com.ubaya.ubayakuliner_160419003_160419038.util.loadImage
import kotlinx.android.synthetic.main.fragment_add_review.*
import kotlinx.android.synthetic.main.fragment_cart.*

/**
 * A simple [Fragment] subclass.
 * Use the [AddReviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddReviewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val transaction = AddReviewFragmentArgs.fromBundle(requireArguments()).transaction

        textReviewNameRestaurant.text = transaction.restaurant.name
        imageReviewRestoPhoto.loadImage(
            "https://hendri-27.github.io/ubayakuliner_db/images"+transaction.restaurant.photoURL,progressLoadingReviewRestoPhoto
        )

        ratingBarAddReview.setOnRatingBarChangeListener { ratingBar, fl, b ->
            buttonSubmit.isEnabled = true
        }
    }
}
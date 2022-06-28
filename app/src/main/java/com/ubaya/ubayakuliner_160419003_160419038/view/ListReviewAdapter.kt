package com.ubaya.ubayakuliner_160419003_160419038.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner_160419003_160419038.R
import com.ubaya.ubayakuliner_160419003_160419038.databinding.ReviewListItemBinding
import com.ubaya.ubayakuliner_160419003_160419038.model.ReviewWithUser
import com.ubaya.ubayakuliner_160419003_160419038.util.loadImage
import kotlinx.android.synthetic.main.review_list_item.view.*

class ListReviewAdapter(val listReviewWithUser:ArrayList<ReviewWithUser>) : RecyclerView.Adapter<ListReviewAdapter.ReviewViewHolder>() {
    class ReviewViewHolder(var view: ReviewListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ReviewListItemBinding.inflate(inflater, parent, false)

        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
//        val reviewWithUser = listReviewWithUser[position]
        with(holder.view) {
            reviewWithUser = listReviewWithUser[position]
//            textReviewUsername.text = reviewWithUser.user.username
//            ratingBarDetailReview.rating = reviewWithUser.review.rating
//            textReview.text = reviewWithUser.review.message
//            textReviewDate.text = reviewWithUser.review.date
//            imageReviewUser.loadImage(
//                reviewWithUser.user.photoURL,
//                progressLoadingReviewUserPhoto
//            )
        }
    }

    override fun getItemCount() = listReviewWithUser.size

    fun updateListReview(newListReviewWithUser: ArrayList<ReviewWithUser>) {
        listReviewWithUser.clear()
        listReviewWithUser.addAll(newListReviewWithUser)
        notifyDataSetChanged()
    }
}
package com.ubaya.ubayakuliner_160419003_160419038.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner_160419003_160419038.databinding.ReviewListItemBinding
import com.ubaya.ubayakuliner_160419003_160419038.model.ReviewWithUser

class ListReviewAdapter(val listReviewWithUser:ArrayList<ReviewWithUser>) : RecyclerView.Adapter<ListReviewAdapter.ReviewViewHolder>() {
    class ReviewViewHolder(var view: ReviewListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ReviewListItemBinding.inflate(inflater, parent, false)

        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        with(holder.view) {
            reviewWithUser = listReviewWithUser[position]
        }
    }

    override fun getItemCount() = listReviewWithUser.size

    fun updateListReview(newListReviewWithUser: ArrayList<ReviewWithUser>) {
        listReviewWithUser.clear()
        listReviewWithUser.addAll(newListReviewWithUser)
        notifyDataSetChanged()
    }
}
package com.ubaya.ubayakuliner_160419003_160419038.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner_160419003_160419038.databinding.CheckoutItemBinding
import com.ubaya.ubayakuliner_160419003_160419038.model.CartWithFood

class CheckoutAdapter(val listCart:ArrayList<CartWithFood>) : RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder>() {
    class CheckoutViewHolder(var view: CheckoutItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CheckoutItemBinding.inflate(inflater, parent, false)

        return CheckoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: CheckoutViewHolder, position: Int) {
        with(holder.view) {
            cartWithFood = listCart[position]
        }
    }

    override fun getItemCount() = listCart.size

    fun updateListCheckout(newListCart: ArrayList<CartWithFood>){
        listCart.clear()
        listCart.addAll(newListCart)
        notifyDataSetChanged()
    }
}
package com.ubaya.ubayakuliner_160419003_160419038.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner_160419003_160419038.R
import com.ubaya.ubayakuliner_160419003_160419038.databinding.DetailTransactionItemBinding
import com.ubaya.ubayakuliner_160419003_160419038.model.Cart
import com.ubaya.ubayakuliner_160419003_160419038.model.CartWithFood
import com.ubaya.ubayakuliner_160419003_160419038.util.loadImage
import kotlinx.android.synthetic.main.detail_transaction_item.view.*

class CheckoutAdapter(val listCart:ArrayList<CartWithFood>) : RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder>() {
    class CheckoutViewHolder(var view: DetailTransactionItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DetailTransactionItemBinding.inflate(inflater, parent, false)

        return CheckoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: CheckoutViewHolder, position: Int) {
        with(holder.view) {
            detailTransWithFood = listCart[position]
        }
        val cartWithFood = listCart[position]
        val cart = cartWithFood.cart
        val food = cartWithFood.food

        with(holder.view) {
            textDetailTransFoodName.text = food.name
            textDetailTransQty.text = "${cart.qty} x"
            textDetailTransFoodPrice.text = String.format("Rp%,d", cart.qty * food.price)

            imageDetailTransFood.loadImage(
                "https://hendri-27.github.io/ubayakuliner_db/images" + food.photoURL,
                progressLoadingDetailTransFoodPhoto
            )
        }
    }

    override fun getItemCount() = listCart.size

    fun updateListCheckout(newListCart: ArrayList<CartWithFood>){
        listCart.clear()
        listCart.addAll(newListCart)
        notifyDataSetChanged()
    }
}
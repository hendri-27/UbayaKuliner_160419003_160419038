package com.ubaya.ubayakuliner_160419003_160419038.view

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner_160419003_160419038.databinding.CartListItemBinding
import com.ubaya.ubayakuliner_160419003_160419038.model.CartWithFood
import com.ubaya.ubayakuliner_160419003_160419038.viewmodel.ListCartViewModel
import kotlinx.android.synthetic.main.fragment_cart.*

class ListCartAdapter(val listCartWithFood:ArrayList<CartWithFood>, val parentView:CartFragment) : RecyclerView.Adapter<ListCartAdapter.CartViewHolder>(),
        ButtonIncreaseFICListener, ButtonDecreaseFICListener
{
    class CartViewHolder(var view: CartListItemBinding): RecyclerView.ViewHolder(view.root)

    private lateinit var viewModel: ListCartViewModel
    private var subTotal:Int=0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CartListItemBinding.inflate(inflater, parent, false)

        viewModel = ViewModelProvider(parentView).get(ListCartViewModel::class.java)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        with(holder.view) {
            cartWithFood = listCartWithFood[position]
            increaseListener = this@ListCartAdapter
            decreaseListener = this@ListCartAdapter
        }
    }

    override fun getItemCount() = listCartWithFood.size

    fun setSubTotal(total:Int){
        subTotal = total
    }

    fun updateListFood(newListCart: ArrayList<CartWithFood>){
        listCartWithFood.clear()
        listCartWithFood.addAll(newListCart)
        notifyDataSetChanged()
    }

    override fun onButtonIncreaseCLick(v: View, obj: CartWithFood) {
        if (obj.cart!!.qty < 200) {
            obj.cart!!.qty += 1
            viewModel.update(obj.food.id, obj.cart.qty)
            subTotal += obj.food.price
            parentView.textCartSubtotal.text = String.format("Rp%,d", subTotal)
            notifyDataSetChanged()

            if (obj.cart!!.qty + 1 >= 200 ){
                v.isEnabled = false
                (v as ImageView).setColorFilter(
                    ContextCompat.getColor(v.context, android.R.color.darker_gray),
                    PorterDuff.Mode.MULTIPLY)
            }
        }
    }

    override fun onButtonDecreaseCLick(v: View, btnIncrease: ImageView, quantity: TextView, obj: CartWithFood) {
        if (obj.cart!!.qty - 1 > 0){
            obj.cart!!.qty -= 1
            viewModel.update(obj.food.id, obj.cart!!.qty)
            if (!btnIncrease.isEnabled){
                btnIncrease.isEnabled = true
                btnIncrease.setColorFilter(
                    Color.parseColor("#DC5959"),
                    PorterDuff.Mode.MULTIPLY)
            }
        }else{
            val deletedCart = obj.cart!!.copy()
            listCartWithFood.remove(obj)
            viewModel.delete(deletedCart)
            notifyDataSetChanged()
        }
        subTotal -= obj.food.price
        parentView.textCartSubtotal.text = String.format("Rp%,d", subTotal)
    }
}
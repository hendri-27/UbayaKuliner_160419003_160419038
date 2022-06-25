package com.ubaya.ubayakuliner_160419003_160419038.view

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner_160419003_160419038.R
import com.ubaya.ubayakuliner_160419003_160419038.model.CartWithFood
import com.ubaya.ubayakuliner_160419003_160419038.util.loadImage
import com.ubaya.ubayakuliner_160419003_160419038.viewmodel.ListCartViewModel
import kotlinx.android.synthetic.main.cart_list_item.view.*
import kotlinx.android.synthetic.main.fragment_cart.*

class ListCartAdapter(val listCartWithFood:ArrayList<CartWithFood>, val viewParent:CartFragment,
val viewModel: ListCartViewModel) : RecyclerView.Adapter<ListCartAdapter.CartViewHolder>() {
    class CartViewHolder(var view: View): RecyclerView.ViewHolder(view)
    private var subTotal:Int=0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cart_list_item, parent, false)

        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartWithFood = listCartWithFood[position]
        val cart = cartWithFood.cart
        val food = cartWithFood.food

        with(holder.view){
            textCartFoodName.text = food.name
            textCartFoodPrice.text = String.format("Rp%,d", food.price)
            textQtyCartFoodCounter.text = cart.qty.toString()
            imageCartFood.loadImage("https://hendri-27.github.io/ubayakuliner_db/images"+food.photoURL,progressLoadingCartFoodPhoto)

            buttonDecreaseFIC.setOnClickListener {
                val qty = Integer.parseInt(textQtyCartFoodCounter.text.toString()) - 1

                if (qty <= 0){
                    viewModel.delete(cart)
                    notifyDataSetChanged()

                    if (itemCount == 0){
                        viewParent.textNoDataListCart.visibility = View.VISIBLE
                        viewParent.cardViewCheckout.visibility = View.GONE
                    }
                }else {
                    viewModel.update(food.food_id, qty)
                    textQtyCartFoodCounter.text = qty.toString()

                    if (!buttonIncreaseFIC.isEnabled){
                        buttonIncreaseFIC.isEnabled = true
                        buttonIncreaseFIC.setColorFilter(
                            Color.parseColor("#DC5959"),
                            PorterDuff.Mode.MULTIPLY)
                    }
                }
                subTotal -= food.price
                viewParent.textCartSubtotal.text = String.format("Rp%,d", subTotal)
            }

            buttonIncreaseFIC.setOnClickListener {
                val qty = Integer.parseInt(textQtyCartFoodCounter.text.toString()) + 1

                if (buttonIncreaseFIC.isEnabled){
                    subTotal += food.price
                    viewParent.textCartSubtotal.text = String.format("Rp%,d", subTotal)
                    viewModel.update(food.food_id, qty)
                    textQtyCartFoodCounter.text = qty.toString()
                }

//                if (qty >= cart.food.stock){
//                    buttonIncreaseFIC.isEnabled = false
//                    buttonIncreaseFIC.setColorFilter(
//                        ContextCompat.getColor(context, android.R.color.darker_gray),
//                        PorterDuff.Mode.MULTIPLY)
//                }
            }
        }
    }

    override fun getItemCount() = listCartWithFood.size

    fun setSubTotal(total:Int){
        subTotal = total
    }

    fun getSubTotal(): Int {
        return subTotal
    }

    fun updateListFood(newListCart: ArrayList<CartWithFood>){
        listCartWithFood.clear()
        listCartWithFood.addAll(newListCart)
        notifyDataSetChanged()
    }
}
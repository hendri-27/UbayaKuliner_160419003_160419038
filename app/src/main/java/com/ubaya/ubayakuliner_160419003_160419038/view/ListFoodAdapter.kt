package com.ubaya.ubayakuliner_160419003_160419038.view

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner_160419003_160419038.R
import com.ubaya.ubayakuliner_160419003_160419038.model.Cart
import com.ubaya.ubayakuliner_160419003_160419038.model.FoodWithCart
import com.ubaya.ubayakuliner_160419003_160419038.util.loadImage
import com.ubaya.ubayakuliner_160419003_160419038.util.userId
import com.ubaya.ubayakuliner_160419003_160419038.viewmodel.DetailRestaurantViewModel
import kotlinx.android.synthetic.main.food_list_item.view.*

class ListFoodAdapter(val listFoodWithCart:ArrayList<FoodWithCart>, var viewModel: DetailRestaurantViewModel) : RecyclerView.Adapter<ListFoodAdapter.FoodViewHolder>() {
    class FoodViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.food_list_item, parent, false)

        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val foodWithCart = listFoodWithCart[position]
        with(holder.view){
            textFoodName.text = foodWithCart.food.name
            textFoodPrice.text = String.format("Rp%,d", foodWithCart.food.price)

            imageFood.loadImage("https://hendri-27.github.io/ubayakuliner_db/images"+foodWithCart.food.photoURL,progressLoadingFoodPhoto)

            if (foodWithCart.cart != null){
                cardQtyCounter.visibility = View.VISIBLE
                buttonAddCart.visibility = View.GONE
                textQtyFoodCounter.text = foodWithCart.cart!!.qty.toString()
                if (foodWithCart.cart!!.qty == 199){
                    buttonIncreaseFIR.isEnabled = false
                    buttonIncreaseFIR.setColorFilter(
                        ContextCompat.getColor(context, android.R.color.darker_gray),
                        PorterDuff.Mode.MULTIPLY)
                }
            }

            buttonAddCart.setOnClickListener {
                cardQtyCounter.visibility = View.VISIBLE
                buttonAddCart.visibility = View.GONE

                val newCart = Cart(userId,foodWithCart.food.food_id,1)
                foodWithCart.cart = newCart
                addNewCart(newCart)
                textQtyFoodCounter.text = "1"
            }

            buttonDecreaseFIR.setOnClickListener {
                if (foodWithCart.cart!!.qty - 1 > 0){
                    foodWithCart.cart!!.qty -= 1
                    textQtyFoodCounter.text = foodWithCart.cart!!.qty.toString()
                    if (!buttonIncreaseFIR.isEnabled){
                        buttonIncreaseFIR.isEnabled = true
                        buttonIncreaseFIR.setColorFilter(
                            Color.parseColor("#DC5959"),
                            PorterDuff.Mode.MULTIPLY)
                    }
                }else{
                    val deletedCart = foodWithCart.cart!!
                    foodWithCart.cart = null
                    deleteCart(deletedCart)

                    buttonAddCart.visibility = View.VISIBLE
                    cardQtyCounter.visibility = View.GONE
                }
            }

            buttonIncreaseFIR.setOnClickListener {
                if (foodWithCart.cart!!.qty < 200){
                    foodWithCart.cart!!.qty += 1
                    updateCart(foodWithCart.food.food_id,foodWithCart.cart!!.qty)
                    textQtyFoodCounter.text = "${foodWithCart.cart!!.qty}"
                    if (foodWithCart.cart!!.qty + 1 >= 199 ){
                        buttonIncreaseFIR.isEnabled = false
                        buttonIncreaseFIR.setColorFilter(
                            ContextCompat.getColor(context, android.R.color.darker_gray),
                            PorterDuff.Mode.MULTIPLY)
                    }
                }
            }
        }
    }

    override fun getItemCount() = listFoodWithCart.size

    fun updateListFoodWithCart(newListFoodWithCart: ArrayList<FoodWithCart>){
        listFoodWithCart.clear()
        listFoodWithCart.addAll(newListFoodWithCart)
        notifyDataSetChanged()
    }

    fun addNewCart(newCart: Cart){
        viewModel.insertCart(newCart)
        notifyDataSetChanged()
    }

    fun updateCart(foodId:Int, qty:Int){
        viewModel.updateCart(foodId,qty)
        notifyDataSetChanged()
    }

    fun deleteCart(cart:Cart){
        viewModel.deleteCart(cart)
        notifyDataSetChanged()
    }
}
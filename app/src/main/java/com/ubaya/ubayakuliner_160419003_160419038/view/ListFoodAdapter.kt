package com.ubaya.ubayakuliner_160419003_160419038.view

import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner_160419003_160419038.databinding.FoodListItemBinding
import com.ubaya.ubayakuliner_160419003_160419038.model.Cart
import com.ubaya.ubayakuliner_160419003_160419038.model.FoodWithCart
import com.ubaya.ubayakuliner_160419003_160419038.util.userId
import com.ubaya.ubayakuliner_160419003_160419038.viewmodel.DetailRestaurantViewModel

class ListFoodAdapter(val listFoodWithCart:ArrayList<FoodWithCart>, val parentView:DetailRestaurantFragment) : RecyclerView.Adapter<ListFoodAdapter.FoodViewHolder>(),
        ButtonAddCartListener, ButtonIncreaseFIRListener, ButtonDecreaseFIRListener
{
    class FoodViewHolder(var view: FoodListItemBinding): RecyclerView.ViewHolder(view.root)

    private lateinit var viewModel: DetailRestaurantViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = FoodListItemBinding.inflate(inflater, parent, false)

        viewModel = ViewModelProvider(parentView).get(DetailRestaurantViewModel::class.java)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        with(holder.view){
            foodWithCart = listFoodWithCart[position]
            addToCartListener = this@ListFoodAdapter
            increaseListener = this@ListFoodAdapter
            decreaseListener = this@ListFoodAdapter
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

    override fun onButtonAddCartCLick(v: View, cardQty: CardView, obj: FoodWithCart) {
        val validated = viewModel.validateCart(obj.food.restaurantId)

        if (validated){
            cardQty.visibility = View.VISIBLE
            v.visibility = View.GONE
            val newCart = Cart(userId, obj.food.id,1)
            obj.cart = newCart
            addNewCart(newCart)
        }else{
            Toast.makeText(v.context,"You already have another restaurant food in your cart!",Toast.LENGTH_LONG).show()
        }
    }

    override fun onButtonIncreaseCLick(v: View, btnIncrease: ImageView, obj: FoodWithCart) {
        if (obj.cart!!.qty < 200){
            obj.cart!!.qty += 1
            updateCart(obj.food.id, obj.cart!!.qty)
            if (obj.cart!!.qty + 1 >= 200 ){
                btnIncrease.isEnabled = false
                btnIncrease.setColorFilter(
                    ContextCompat.getColor(v.context, android.R.color.darker_gray),
                    PorterDuff.Mode.MULTIPLY)
            }
        }
    }

    override fun onButtonDecreaseCLick(
        v: View,
        btnIncrease: ImageView,
        btnAddCart: Button,
        cardQty: CardView,
        obj: FoodWithCart
    ) {
        if (obj.cart!!.qty - 1 > 0){
            obj.cart!!.qty -= 1
            updateCart(obj.food.id, obj.cart!!.qty)
            if (!btnIncrease.isEnabled){
                btnIncrease.isEnabled = true
                btnIncrease.setColorFilter(
                    Color.parseColor("#DC5959"),
                    PorterDuff.Mode.MULTIPLY)
            }
        }else{
            val deletedCart = obj.cart!!.copy()
            obj.cart = null
            deleteCart(deletedCart)

            btnAddCart.visibility = View.VISIBLE
            cardQty.visibility = View.GONE
        }
    }
}
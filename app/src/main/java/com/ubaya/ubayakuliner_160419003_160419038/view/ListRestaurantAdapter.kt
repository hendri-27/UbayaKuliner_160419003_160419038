package com.ubaya.ubayakuliner_160419003_160419038.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner_160419003_160419038.databinding.RestaurantListItemBinding
import com.ubaya.ubayakuliner_160419003_160419038.model.Restaurant

class ListRestaurantAdapter(val listRestaurant:ArrayList<Restaurant>) : RecyclerView.Adapter<ListRestaurantAdapter.RestaurantViewHolder>(),RestaurantCardClickListener {
    class RestaurantViewHolder(var view: RestaurantListItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = RestaurantListItemBinding.inflate(inflater, parent, false)

        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        with(holder.view){
            restaurant = listRestaurant[position]
            cardClickListener = this@ListRestaurantAdapter
        }
    }

    override fun getItemCount() = listRestaurant.size

    fun updateListRestaurant(newListRestaurant: ArrayList<Restaurant>){
        listRestaurant.clear()
        listRestaurant.addAll(newListRestaurant)
        notifyDataSetChanged()
    }

    override fun onCardClick(v: View, restoId: Int) {
        val action = ListRestaurantFragmentDirections.actionDetailRestaurant(restoId)
        Navigation.findNavController(v).navigate(action)
    }
}
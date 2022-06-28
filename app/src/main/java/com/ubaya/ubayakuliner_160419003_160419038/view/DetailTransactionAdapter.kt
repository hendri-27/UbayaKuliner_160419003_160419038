package com.ubaya.ubayakuliner_160419003_160419038.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner_160419003_160419038.R
import com.ubaya.ubayakuliner_160419003_160419038.databinding.DetailTransactionItemBinding
import com.ubaya.ubayakuliner_160419003_160419038.model.DetailTransactionWithFood
import com.ubaya.ubayakuliner_160419003_160419038.util.loadImage
import kotlinx.android.synthetic.main.detail_transaction_item.view.*


class DetailTransactionAdapter(val listDetailTransaction:ArrayList<DetailTransactionWithFood>) : RecyclerView.Adapter<DetailTransactionAdapter.DetailTransactionViewHolder>()
{
    class DetailTransactionViewHolder(var view: DetailTransactionItemBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailTransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DetailTransactionItemBinding.inflate(inflater, parent, false)

        return DetailTransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailTransactionViewHolder, position: Int) {
        with(holder.view){
            detailTransWithFood = listDetailTransaction[position]
        }
//        val detailTransaction = listDetailTransaction[position]
//        val food = detailTransaction.food
//
//        with(holder.view){
//            textDetailTransFoodName.text = detailTransaction.food.name
//            textDetailTransFoodPrice.text = String.format("Rp%,d",detailTransaction.detail_price)
//            textDetailTransQty.text = "${detailTransaction.qty} x"
//
//            imageDetailTransFood.loadImage("https://hendri-27.github.io/ubayakuliner_db/images"+ food.photoURL, progressLoadingDetailTransFoodPhoto)
//        }
    }

    override fun getItemCount() = listDetailTransaction.size

    fun updateListDetailTransaction(newListDetailTransaction: ArrayList<DetailTransactionWithFood>){
        listDetailTransaction.clear()
        listDetailTransaction.addAll(newListDetailTransaction)
        notifyDataSetChanged()
    }
}
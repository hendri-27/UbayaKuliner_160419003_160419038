package com.ubaya.ubayakuliner_160419003_160419038.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner_160419003_160419038.databinding.TransactionListItemBinding
import com.ubaya.ubayakuliner_160419003_160419038.model.Transaction
import com.ubaya.ubayakuliner_160419003_160419038.model.TransactionWithRestaurant
import com.ubaya.ubayakuliner_160419003_160419038.viewmodel.ListTransactionViewModel

class ListTransactionAdapter(val listTransaction:ArrayList<TransactionWithRestaurant>, val parentView:ListTransactionFragment) : RecyclerView.Adapter<ListTransactionAdapter.TransactionViewHolder>(),ButtonRateClickListener,TransactionCardClickListener {
    class TransactionViewHolder(var view: TransactionListItemBinding) : RecyclerView.ViewHolder(view.root)

    private lateinit var viewModel: ListTransactionViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = TransactionListItemBinding.inflate(inflater, parent, false)

        viewModel = ViewModelProvider(parentView).get(ListTransactionViewModel::class.java)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        with(holder.view) {
            transWithResto = listTransaction[position]
            btnRateClickListener = this@ListTransactionAdapter
            cardClickListener = this@ListTransactionAdapter
        }
    }

    override fun getItemCount() = listTransaction.size

    fun updateListTransaction(newListTransaction: ArrayList<TransactionWithRestaurant>) {
        listTransaction.clear()
        listTransaction.addAll(newListTransaction)
        notifyDataSetChanged()
    }

    override fun onButtonRateClick(v: View, trans:Transaction) {
        if (v.tag.equals("Ongoing")){
            trans.status = "Completed"
            viewModel.updateStatus(trans.id)
            notifyDataSetChanged()
        }else{
            if (trans.rate == null){
                val action = ListTransactionFragmentDirections.actionAddReviewFragment(trans.id)
                Navigation.findNavController(v).navigate(action)
            }
        }
    }

    override fun onCardClick(v: View, restoName: String, transId: String) {
        val action = ListTransactionFragmentDirections.actionDetailTransactionFragment(restoName, transId)
        Navigation.findNavController(v).navigate(action)
    }
}
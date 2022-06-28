package com.ubaya.ubayakuliner_160419003_160419038.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner_160419003_160419038.databinding.TransactionListItemBinding
import com.ubaya.ubayakuliner_160419003_160419038.model.TransactionWithRestaurant
import com.ubaya.ubayakuliner_160419003_160419038.viewmodel.ListTransactionViewModel

class ListTransactionAdapter(val listTransaction:ArrayList<TransactionWithRestaurant>,val viewModel: ListTransactionViewModel) : RecyclerView.Adapter<ListTransactionAdapter.TransactionViewHolder>(),ButtonRateClickListener,ButtonCompleteClickListener,TransactionCardClickListener {
    class TransactionViewHolder(var view: TransactionListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = TransactionListItemBinding.inflate(inflater, parent, false)

        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
//        val transactionWithRestaurant = listTransaction[position]
//        val transaction = transactionWithRestaurant.transaction
//        val restaurant = transactionWithRestaurant.restaurant

        with(holder.view) {
            transWithResto = listTransaction[position]

//            textTransactionId.text = transaction.id
//            textTransactionDate.text = transaction.date
//            textTransactionRestoName.text = restaurant.name
//            textTransactionGrandtotal.text = String.format("Rp%,d - %s", transaction.grandTotal, transaction.paymentMethod)
//            textHistoryStatus.text = transaction.status
//            textTransactionGrandtotal.text = String.format("Rp%,d", transaction.grandTotal)

//            cardTransaction.setOnClickListener {
//                val action = ListTransactionFragmentDirections.actionDetailTransactionFragment(restaurant.name, transaction.id)
//                Navigation.findNavController(it).navigate(action)
//            }

//            imageTransactionResto.loadImage(
//                "https://hendri-27.github.io/ubayakuliner_db/images"+restaurant.photoURL,
//                progressLoadingTransactionRestoPhoto
//            )

//            if (transaction.rate != null){
//                buttonRate.isEnabled = false
//                buttonRate.text = "Rated"
//            }else {
//                buttonRate.setOnClickListener {
//                    val action = ListTransactionFragmentDirections.actionAddReviewFragment(transaction.id)
//                    Navigation.findNavController(it).navigate(action)
//                }
//            }
        }
    }

    override fun getItemCount() = listTransaction.size

    fun updateListTransaction(newListTransaction: ArrayList<TransactionWithRestaurant>) {
        listTransaction.clear()
        listTransaction.addAll(newListTransaction)
        notifyDataSetChanged()
    }

    override fun onButtonRateClick(v: View, transId: String) {
        val action = ListTransactionFragmentDirections.actionAddReviewFragment(transId)
        Navigation.findNavController(v).navigate(action)
    }

    override fun onButtonCompleteClick(v: View, transId: String) {
        viewModel.updateStatus(transId)
    }

    override fun onCardClick(v: View, restoName: String, transId: String) {
        val action = ListTransactionFragmentDirections.actionDetailTransactionFragment(restoName, transId)
        Navigation.findNavController(v).navigate(action)
    }
}
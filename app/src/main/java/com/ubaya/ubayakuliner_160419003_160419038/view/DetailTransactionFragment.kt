package com.ubaya.ubayakuliner_160419003_160419038.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayakuliner_160419003_160419038.databinding.FragmentDetailTransactionBinding
import com.ubaya.ubayakuliner_160419003_160419038.viewmodel.DetailTransactionViewModel
import kotlinx.android.synthetic.main.fragment_detail_restaurant.*
import kotlinx.android.synthetic.main.fragment_detail_transaction.*

/**
 * A simple [Fragment] subclass.
 * Use the [DetailTransactionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailTransactionFragment : Fragment() {
    private lateinit var viewModel: DetailTransactionViewModel
    private val detailFoodListAdapter = DetailTransactionAdapter(arrayListOf())
    private lateinit var dataBinding: FragmentDetailTransactionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentDetailTransactionBinding.inflate(inflater, container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(DetailTransactionViewModel::class.java)

        val restaurantName = DetailTransactionFragmentArgs.fromBundle(requireArguments()).restaurantName
        val transactionId = DetailTransactionFragmentArgs.fromBundle(requireArguments()).transactionId
        viewModel.fetch(transactionId)

        textDetailTransRestoName.text = restaurantName
        textDetailTransRestoName2.text = restaurantName

        recViewDetailTrans.layoutManager = LinearLayoutManager(context)
        recViewDetailTrans.adapter = detailFoodListAdapter

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.detailTransactionLiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()){
                textNoDataDetailTransaction.visibility = View.GONE
                dataBinding.transaction = it[0].transaction
                detailFoodListAdapter.updateListDetailTransaction(it)
            }else {
                textNoDataDetailTransaction.visibility = View.VISIBLE
            }
        }
        viewModel.detailTransactionLoadErrorLiveData.observe(viewLifecycleOwner){
            textErrorDetailTransaction.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.detailTransactionLoadingLiveData.observe(viewLifecycleOwner){
            if (it){ //Sedang Load
                recViewDetailTrans.visibility = View.GONE
                progressLoadDetailTransaction.visibility = View.VISIBLE
            }else{
                recViewDetailTrans.visibility = View.VISIBLE
                progressLoadDetailTransaction.visibility = View.GONE
            }
        }
    }
}
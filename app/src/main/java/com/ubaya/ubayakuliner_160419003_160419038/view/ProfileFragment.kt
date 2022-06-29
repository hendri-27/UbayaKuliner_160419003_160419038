package com.ubaya.ubayakuliner_160419003_160419038.view

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ubaya.ubayakuliner_160419003_160419038.R
import com.ubaya.ubayakuliner_160419003_160419038.databinding.FragmentProfileBinding
import com.ubaya.ubayakuliner_160419003_160419038.util.arrGender
import com.ubaya.ubayakuliner_160419003_160419038.util.loadImage
import com.ubaya.ubayakuliner_160419003_160419038.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_detail_restaurant.*
import kotlinx.android.synthetic.main.fragment_profile.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment(), ButtonProfileCLickListener, CalendarClickListener {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var dataBinding: FragmentProfileBinding
    private val myCalendar: Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_profile, container, false
        )

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = ArrayAdapter(view.context, R.layout.myspinner_layout, arrGender)
        adapter.setDropDownViewResource(R.layout.myspinner_item_layout)
        dataBinding.spinnerGender.adapter = adapter
        dataBinding.btnSaveClickListener = this
        dataBinding.calendarListener = this

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.fetch()

        observeViewModel(adapter)
    }

    private fun updateLabel() {
        val myFormat = "dd-MM-yyyy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        dataBinding.textInputBOD.setText(dateFormat.format(myCalendar.time))
    }

    private fun observeViewModel(adapter:ArrayAdapter<String>){
        viewModel.profileLiveData.observe(viewLifecycleOwner) {
            dataBinding.user = it
            spinnerGender.setSelection(adapter.getPosition(it.gender))
        }

        viewModel.profileLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorProfile.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.profileLoadingLiveData.observe(viewLifecycleOwner) {
            if (it){
                scrollViewProfile.visibility = View.GONE
                progressLoadProfile.visibility = View.VISIBLE
            }else{
                scrollViewProfile.visibility = View.VISIBLE
                progressLoadProfile.visibility = View.GONE
            }
        }
    }

    override fun onButtonSaveChangeClick(v: View) {
        viewModel.update(
            dataBinding.user!!.name,
            dataBinding.spinnerGender.selectedItem.toString(),
            dataBinding.user!!.birthDate,
            dataBinding.user!!.phoneNumber,
            dataBinding.user!!.email,
            dataBinding.user!!.password)
    }

    override fun onCalendarClick(v: View) {
        DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day
                updateLabel()
            },
            myCalendar[Calendar.YEAR],
            myCalendar[Calendar.MONTH],
            myCalendar[Calendar.DAY_OF_MONTH]
        ).show()
    }
}
package com.example.elegant_app.staff

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentFeeMgtBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.time.Duration
import java.util.*
import kotlin.properties.Delegates

class FeeMgtFragment : Fragment() {
lateinit var binding:FragmentFeeMgtBinding
    lateinit var selected_paiddate:String
    lateinit var studentName:String
    lateinit var studentClass:String
    lateinit var std:String
    lateinit var division:String
    lateinit var amount :String
    lateinit var month:String
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        binding=FragmentFeeMgtBinding.inflate(layoutInflater,container,false)
        setupSpinner_For_feeclass()
        setupSpinner_For_feedivision()
        setupSpinner_For_feemonth()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etFeepaidDate.setOnClickListener(){
            val c = Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val selectedDate =
                        (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding.etFeepaidDate.setText(selectedDate)
                    selected_paiddate=selectedDate
                },
                year, month, day
            )


            datePickerDialog.show()
        }

        binding.btnSubmit.setOnClickListener(){
            studentName=binding.etFeeSName.text.toString()
            amount=binding.etFeeAmount.text.toString()
            studentClass="${std} - ${division}"

           /* FeeModel(7,studentName,std,amount,selected_paiddate,month)
            Snackbar.make(it,"Fee added successfully",Snackbar.LENGTH_LONG).show()*/

           if (FeeFormValidate())
           {
               val fireStoreDatabase = FirebaseFirestore.getInstance()
               val obj = FeeModel(studentName,studentClass,month,amount,selected_paiddate) // obj of modelclass

               fireStoreDatabase.collection("Fee_Paid")
                   .add(obj)
                   .addOnSuccessListener {
                       Toast.makeText(requireContext(), "Data added", Toast.LENGTH_LONG).show()
                       binding.etFeeSName.text.clear()
                       binding.etFeeAmount.text.clear()
                       binding.etFeepaidDate.text.clear()

                       //Log.d(TAG, "Added document with ID ${it.id}")
                   }
                   .addOnFailureListener { exception ->
                       Toast.makeText(requireContext(), "Error Occured", Toast.LENGTH_LONG).show()
                       //Log.w(TAG, "Error adding document $exception")
                   }

               Snackbar.make(it,"Fee added successfully",Snackbar.LENGTH_LONG).show()

           }


        }
    }


    private fun setupSpinner_For_feeclass() {
        val classNames = arrayOf("Select Class","+1","+2","10","9","8","7","6","5","4","UG","Others")
        val spinner = binding.SpinnerFeeClass
        val arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, classNames)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                std=spinner.selectedItem.toString()
                //todo action
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

    }

    private fun setupSpinner_For_feedivision() {
        val divNames = arrayOf("Select Division","A","B","C","D","E","F","G","UG","Others")
        val spinner = binding.SpinnerFeeDiv
        val arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, divNames)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                division=spinner.selectedItem.toString()
                //todo action
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

    }

    private fun setupSpinner_For_feemonth() {
        val monthNames = arrayOf("Paid For","January","February","March","April","May","June","July","August","September","October","November","December")
        val spinner = binding.SpinnerFeeMonth
        val arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, monthNames)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                month=spinner.selectedItem.toString()
                //todo action
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

    }

    private fun FeeFormValidate(): Boolean{
        if (
            binding.etFeeSName.text.isNotBlank() && studentClass.isNotBlank() && month.isNotBlank()  &&
            amount.isNotBlank() && selected_paiddate.isNotBlank()
        )
        {
            return true
        }
        else
        {
            Toast.makeText(requireContext(), "All fields are mandatory", Toast.LENGTH_LONG).show()
           // Snackbar.make(it,"Fee added successfully",Snackbar.LENGTH_LONG).show()
            return false

        }

    }
}
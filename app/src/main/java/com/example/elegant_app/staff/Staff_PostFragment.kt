package com.example.elegant_app.staff

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentStaffPostBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class Staff_PostFragment : Fragment() {
    private lateinit var binding:FragmentStaffPostBinding
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    var announcement:String?=null
    var date:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        binding=FragmentStaffPostBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            etDate.setOnClickListener(){

                val current = LocalDateTime.now()

                val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                val formatted = current.format(formatter)

                println("Current Date is: $formatted")
                etDate.setText(formatted)


            }

            etTPostmessage.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.toString().isNotEmpty())

                    {
                        binding.etTPostmessage.setBackgroundResource(R.drawable.curved)
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }

            })
            etDate.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.toString().isNotEmpty())

                    {
                        binding.etDate.setBackgroundResource(R.drawable.curved)
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }

            })

            btnSignin.setOnClickListener(){
                announcement= binding.etTPostmessage.text.toString()
                date= binding.etDate.text.toString()

               if (validator() && date_Validator()) {
                   Snackbar.make(it,"Success",Snackbar.LENGTH_LONG).show()
                   val fireStoreDatabase = FirebaseFirestore.getInstance()
                   val obj = AnnouncementModel(announcement!!, date!!) // obj of modelclass

                   fireStoreDatabase.collection("Announcement")
                       .add(obj)
                       .addOnSuccessListener {
                           Toast.makeText(requireContext(), "Data added", Toast.LENGTH_LONG).show()
                           binding.etDate.text.clear()
                           binding.etTPostmessage.text.clear()

                           //Log.d(TAG, "Added document with ID ${it.id}")
                       }
                       .addOnFailureListener { exception ->
                           Toast.makeText(requireContext(), "Error Occured", Toast.LENGTH_LONG).show()
                           //Log.w(TAG, "Error adding document $exception")
                       }

               }

            }
        }
    }

    fun validator() :Boolean{
        if (binding.etTPostmessage.text.isBlank() || binding.etTPostmessage.text.isEmpty()
             ){
            binding.etTPostmessage.setBackgroundResource(R.drawable.error_curved)
            return false
        }
        else
        {
            return true}
    }

    fun date_Validator() :Boolean{
        if (binding.etDate.text.isBlank() || binding.etDate.text.isEmpty()
        ){
            binding.etDate.setBackgroundResource(R.drawable.error_curved)
            return false
        }
        else
        {
            return true}
    }

}
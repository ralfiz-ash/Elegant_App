package com.example.elegant_app.teacher

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elegant_app.databinding.FragmentAssignmentBinding
import com.example.elegant_app.staff.TeacherAdapter
import com.example.elegant_app.staff.TeacherModel
import com.example.elegant_app.staff.Teacher_listFragmentDirections
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class AssignmentFragment : Fragment() {
    private lateinit var binding: FragmentAssignmentBinding
    lateinit var subject:String
    lateinit var topic:String
    lateinit var date:String
    lateinit var standard:String
    val taskList = mutableListOf<AssignmentModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAssignmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showData()
        binding.etTasmntdate.setOnClickListener() {
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
                    date=selectedDate
                    binding.etTasmntdate.setText(selectedDate)
                },
                year, month, day
            )

            datePickerDialog.show()
        }

        binding.btnTasmnt.setOnClickListener(){
            subject=binding.etTasmntsub.text.toString()
            topic=binding.etTasmnttopic.text.toString()
           standard=binding.etTasmntStd.text.toString()

            if(AssignmentFormValidate())
            {
                val fireStoreDatabase = FirebaseFirestore.getInstance()
                val obj = AssignmentModel(subject,topic,date,standard) // obj of modelclass

                fireStoreDatabase.collection("Assignment")
                    .add(obj)
                    .addOnSuccessListener {
                        Toast.makeText(requireContext(), "Data added", Toast.LENGTH_LONG).show()
                        binding.etTasmntsub.text.clear()
                        binding.etTasmnttopic.text.clear()
                        binding.etTasmntStd.text.clear()
                        binding.etTasmntdate.text.clear()

                        //Log.d(TAG, "Added document with ID ${it.id}")
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(requireContext(), "Error Occured", Toast.LENGTH_LONG).show()
                        //Log.w(TAG, "Error adding document $exception")
                    }
                Snackbar.make(it,"Assignment Created..", Snackbar.LENGTH_LONG).show()

                //Snackbar.make(,"Fee added successfully", Snackbar.LENGTH_LONG).show()

            }
        }


    }

    private fun AssignmentFormValidate(): Boolean{
        if (
            binding.etTasmntsub.text.isNotBlank() && binding.etTasmnttopic.text.isNotBlank() && binding.etTasmntdate.text.isNotBlank()  &&
            binding.etTasmntStd.text.isNotBlank()
        ) {
            return true
        }
        else
        {
            Toast.makeText(requireContext(), "All fields are mandatory", Toast.LENGTH_LONG).show()
            return false

        }

    }

    private fun showData() {

        taskList.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("Assignment")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    //Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                    taskList.add(
                        AssignmentModel(document.data.get("subject").toString(),document.data.get("topic").toString(),document.data.get("date").toString(),
                        document.data.get("standard").toString())
                    )

                    //}

                    //Log.d(TAG, "showData: ${}")
                }

                binding.rvTasks.layoutManager= LinearLayoutManager(requireContext())
                val adapter= AssignmentAdapter(requireContext(),taskList)
                binding.rvTasks.adapter=adapter

               /* adapter.listItemClick = {
                    findNavController().navigate(Teacher_listFragmentDirections.actionTeacherListFragmentToTeacherProfileFragment(it))
                }*/


            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }
}



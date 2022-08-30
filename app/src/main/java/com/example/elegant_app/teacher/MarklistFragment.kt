package com.example.elegant_app.teacher

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.elegant_app.databinding.FragmentMarklistBinding
import com.example.elegant_app.staff.exams
import com.google.firebase.firestore.FirebaseFirestore


class MarklistFragment : Fragment() {
    private lateinit var binding: FragmentMarklistBinding
    public var personNames = mutableListOf<String>()
    public var exam:String?=null
    public var subject:String?=null
    public var medium:String?=null
    public var std:String?=null
    public var cls:String?=null
    public var div:String?=null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding=FragmentMarklistBinding.inflate(layoutInflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpinner_For_subject()
        setupSpinner_For_medium()
        setupSpinner_For_class()
        setupSpinner_For_division()
        showExams()

        binding.btnTmarknext.setOnClickListener(){
            var sp:SharedPreferences=requireContext().getSharedPreferences("values",Context.MODE_PRIVATE)
            val ed:SharedPreferences.Editor=sp.edit()
            ed.putString("exam",exam)
            ed.putString("subject",subject)
            ed.putString("medium",medium)
            ed.putString("standard",std)
            ed.commit()
            findNavController().navigate(MarklistFragmentDirections.actionMarklistFragmentToTStudentlistFragment())
        }
    }

    private fun showExams() {

        exams.clear()
        exams.add("Choose Exam")
        val db = FirebaseFirestore.getInstance()

        db.collection("Exam")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    // Log.d(ContentValues.TAG, "${document.id} => ${document.data}")

                    exams.add(document.data["examName"].toString())
                    Log.d("examsss", "${exams}")

                }

                setupSpinner()
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }



    }

    private fun setupSpinner() {


        val spinner = binding.Spinner
        val arrayAdapter =ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, exams)
arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                exam= spinner.selectedItem.toString()
                //todo action
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

    }

    private fun setupSpinner_For_subject() {
        val subjectNames = arrayOf("Choose Subject","Physics","Chemistry","Maths","Biology","Malayalam","Hindi","Arabic","Social","English")
        val spinner = binding.Spinnersubject
        val arrayAdapter =ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, subjectNames)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                subject=spinner.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

    }

    private fun setupSpinner_For_medium() {
        val subjectNames = arrayOf("Choose the medium","English","Malayalam")
        val spinner = binding.Spinnermedium
        val arrayAdapter =ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, subjectNames)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                medium=spinner.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

    }

    private fun setupSpinner_For_class() {
        val subjectNames = arrayOf("Choose Standard","10","9","8","7","6","5")
        val spinner = binding.Spinnerclass
        val arrayAdapter =ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, subjectNames)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                cls=spinner.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

    }

    private fun setupSpinner_For_division() {
        val subjectNames = arrayOf("Choose Division","A","B","C","D","E","F","G")
        val spinner = binding.Spinnerdivision
        val arrayAdapter =ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, subjectNames)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                std = "${cls} ${spinner.selectedItem.toString()}"
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

    }



}

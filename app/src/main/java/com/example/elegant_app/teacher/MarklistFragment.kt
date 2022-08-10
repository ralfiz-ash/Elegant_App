package com.example.elegant_app.teacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentMarklistBinding


class MarklistFragment : Fragment() {
    private lateinit var binding: FragmentMarklistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=FragmentMarklistBinding.inflate(layoutInflater,container,false)
        setupSpinner()
        setupSpinner_For_subject()
        setupSpinner_For_medium()
        setupSpinner_For_class()
        setupSpinner_For_division()
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnTmarknext.setOnClickListener(){
            findNavController().navigate(MarklistFragmentDirections.actionMarklistFragmentToTStudentlistFragment())
        }
    }

    private fun setupSpinner() {
        val personNames = arrayOf("Choose Exam Name", "Onam Examination","Half yearly","Monthly Test")
        val spinner = binding.Spinner
        val arrayAdapter =ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, personNames)
arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                //todo action
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

    }

    private fun setupSpinner_For_subject() {
        val subjectNames = arrayOf("Choose Subject","Physics","Chemistry","Maths")
        val spinner = binding.Spinnersubject
        val arrayAdapter =ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, subjectNames)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                //todo action
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
                view: View,
                position: Int,
                id: Long
            ) {
                //todo action
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

    }

    private fun setupSpinner_For_class() {
        val subjectNames = arrayOf("Choose Standard","8","9","10")
        val spinner = binding.Spinnerclass
        val arrayAdapter =ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, subjectNames)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                //todo action
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

    }

    private fun setupSpinner_For_division() {
        val subjectNames = arrayOf("Choose Division","A","B","C")
        val spinner = binding.Spinnerdivision
        val arrayAdapter =ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, subjectNames)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                //todo action
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

    }



}

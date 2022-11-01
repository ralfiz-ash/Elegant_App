package com.example.elegant_app.student

import android.R
import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.elegant_app.Adapters.ExamAdapter
import com.example.elagant.pojo.ExamModel
import com.example.elegant_app.databinding.FragmentStudentExamBinding
import com.google.firebase.firestore.FirebaseFirestore


class StudentExamFragment : Fragment() {
    lateinit var binding: FragmentStudentExamBinding
    var exams = mutableListOf<String>()
    var ecam_value: String = ""
    var ll = mutableListOf<ExamModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentExamBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showExams()
        binding.apply {

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

        val spinner = binding.examspinner
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, exams)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                ecam_value = spinner.selectedItem.toString()
                showData()
                //todo action
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

    }

    private fun showData() {
        val sp: SharedPreferences =
            requireContext().getSharedPreferences("log", Context.MODE_PRIVATE);

        ll.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("Score_card").whereEqualTo("exam", ecam_value)
            .whereEqualTo("standard", sp.getString("div", ""))
            .whereEqualTo("reg_no", sp.getString("reg_no", ""))
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    ll.add(
                        ExamModel(
                            document.data.get("subject").toString(),
                            document.data.get("mark").toString()
                        )
                    )


                }

                binding.examRecycle.layoutManager = GridLayoutManager(requireContext(), 2)
                val adapter = ExamAdapter(requireContext(), ll)
                binding.examRecycle.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }

}

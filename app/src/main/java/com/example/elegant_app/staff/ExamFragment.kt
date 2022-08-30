package com.example.elegant_app.staff

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentExamBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class ExamFragment : Fragment() {
    private lateinit var binding:FragmentExamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentExamBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.card1CreateExam.setOnClickListener(){
            showDialog()
        }

    }
    fun showDialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("New Exam")

// Set up the input
        val input = EditText(this.requireContext())
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setHint("Enter Exam Name")
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

// Set up the buttons
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            // Here you get get input text from the Edittext
            var typedText= input.text.toString()
            if (typedText.isNotEmpty()) {
                //Snackbar.make(it,"Success", Snackbar.LENGTH_LONG).show()
                val fireStoreDatabase = FirebaseFirestore.getInstance()
                val obj = ExamModel(examName = typedText)// obj of modelclass

                fireStoreDatabase.collection("Exam")
                    .add(obj)
                    .addOnSuccessListener {
                        exams.add(typedText.toString())
                        Log.d("examlist", "showDialog: ${exams}")
                        Toast.makeText(requireContext(), "Exam added", Toast.LENGTH_LONG).show()

                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(requireContext(), "Error Occured", Toast.LENGTH_LONG).show()
                        //Log.w(TAG, "Error adding document $exception")
                    }

            }
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }
}
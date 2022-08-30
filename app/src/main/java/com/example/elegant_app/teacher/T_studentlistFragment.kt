package com.example.elegant_app.teacher

import android.content.ContentValues
import android.content.DialogInterface
import android.content.SharedPreferences
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elegant_app.databinding.FragmentTStudentlistBinding
import com.example.elegant_app.staff.*
import com.google.firebase.firestore.FirebaseFirestore


class T_studentlistFragment : Fragment() {
   private lateinit var binding: FragmentTStudentlistBinding
    lateinit var sp: SharedPreferences
   var studentlist= mutableListOf<EStudentModel>()
    lateinit  var adapter:TStudent_list_Adapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       binding=FragmentTStudentlistBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sp= requireContext().getSharedPreferences("values", android.content.Context.MODE_PRIVATE)

        adapter= TStudent_list_Adapter(requireContext(),studentlist)
        binding.rvStudentCards.adapter=adapter
        binding.rvStudentCards.layoutManager=LinearLayoutManager(requireContext())
        showData()

        adapter= TStudent_list_Adapter(requireContext(),studentlist)

    }

//setting mark via dialog
    fun showdialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Entering Mark..")

// Set up the input
        val input = EditText(this.requireContext())
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setHint("Score")
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

// Set up the buttons
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            // Here you get get input text from the Edittext
            var typedText = input.text.toString()
            val fireStoreDatabase = FirebaseFirestore.getInstance()
            val obj = ScoreModel(
                sp.getString("regno",""),
                sp.getString("exam",""),
                sp.getString("subject",""),
                sp.getString("medium",""),
                sp.getString("standard",""),
                typedText,
                sp.getString("name","")) // obj of modelclass

            fireStoreDatabase.collection("Score_card")
                .add(obj)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Data added", Toast.LENGTH_LONG).show()

                }
                .addOnFailureListener { exception ->
                    Toast.makeText(requireContext(), "Error Occured", Toast.LENGTH_LONG).show()
                    //Log.w(TAG, "Error adding document $exception")
                }
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }

    private fun showData() {

        studentlist.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("Students").whereEqualTo("division",sp.getString("standard","error"))
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    // Log.d(ContentValues.TAG, "${document.id} => ${document.data}")

                    studentlist.add(EStudentModel(
                        regno = document.data.get("reg_no").toString(),
                        name = document.data.get("name").toString(),
                        standard = document.data.get("division").toString()))

                }

                 adapter= TStudent_list_Adapter(requireContext(),studentlist)
                binding.rvStudentCards.adapter=adapter
                binding.rvStudentCards.layoutManager=LinearLayoutManager(requireContext())
                adapter.listItemClick= { it ->
                    val ed:SharedPreferences.Editor=sp.edit()
                    ed.putString("regno",it.regno)
                    ed.putString("name",it.name)
                    ed.putString("std",it.standard)
                    ed.commit()
                    showdialog()

                    /* val builder = AlertDialog.Builder(requireContext())
                     //set title for alert dialog
                     builder.setTitle("Enter Mark")
                     builder.set
                     builder.setMessage(R.string.dialogMessage)
                     builder.setIcon(android.R.drawable.ic_dialog_alert)

                     //performing positive action
                     builder.setPositiveButton("Yes"){dialogInterface, which ->
                         Toast.makeText(applicationContext,"clicked yes",Toast.LENGTH_LONG).show()
                     }
                     //performing cancel action
                     builder.setNeutralButton("Cancel"){dialogInterface , which ->
                         Toast.makeText(applicationContext,"clicked cancel\n operation cancel",Toast.LENGTH_LONG).show()
                     }
                     //performing negative action
                     builder.setNegativeButton("No"){dialogInterface, which ->
                         Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
                     }
                     // Create the AlertDialog
                     val alertDialog: AlertDialog = builder.create()
                     // Set other dialog properties
                     alertDialog.setCancelable(false)
                     alertDialog.show()*/
                }

            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }

    }

    }





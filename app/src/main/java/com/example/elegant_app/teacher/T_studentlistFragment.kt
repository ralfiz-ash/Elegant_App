package com.example.elegant_app.teacher

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentTStudentlistBinding


class T_studentlistFragment : Fragment() {
   private lateinit var binding: FragmentTStudentlistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=FragmentTStudentlistBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val student_list= arrayListOf(
            StudentModel(1,"Nithin"), StudentModel(2,"Zenith"),
            StudentModel(3,"Vishnu"),StudentModel(4,"Bose"),
            StudentModel(5,"Ashiq"),StudentModel(6,"Libaanu")
        )
        binding.rvStudentCards.layoutManager=LinearLayoutManager(requireContext())
        val adapter= TStudent_list_Adapter(requireContext(),student_list)
        binding.rvStudentCards.adapter=adapter

        adapter.listItemClick= { it ->
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
            var m_Text = input.text.toString()
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }

    }





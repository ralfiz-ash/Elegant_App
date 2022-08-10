package com.example.elegant_app.teacher

import android.app.DatePickerDialog
import android.content.DialogInterface.OnMultiChoiceClickListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.elegant_app.databinding.FragmentMakeprofileBinding
import java.util.*
import kotlin.collections.ArrayList


class MakeprofileFragment : Fragment() {
    private lateinit var binding: FragmentMakeprofileBinding
    lateinit var list:ArrayList<String>
    lateinit var selectedlist :ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentMakeprofileBinding.inflate(layoutInflater,container,false)
        list= arrayListOf("Malayalam","English")
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etTdob.setOnClickListener(){
            val c = Calendar.getInstance()

            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { view, year, monthOfYear, dayOfMonth ->

                    val selectedDate =
                        (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding.etTdob.setText(selectedDate)
                },
                year, month, day
            )

            datePickerDialog.show()
        }


    }


}
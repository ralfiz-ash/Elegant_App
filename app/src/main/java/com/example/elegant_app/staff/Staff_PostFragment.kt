package com.example.elegant_app.staff

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentStaffPostBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class Staff_PostFragment : Fragment() {
    private lateinit var binding:FragmentStaffPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

               /* val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)
                val hour = c.get(Calendar.HOUR)
                val minute = c.get(Calendar.MINUTE)
                val zone= c.get(Calendar.AM_PM)

                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { view, year, monthOfYear, dayOfMonth ->

                        val selectedDate =
                            (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                        binding.etDate.setText(selectedDate)
                    },
                    year, month, day
                )
                val TimePicker: TimePickerDialog
                *//*val e:String
                e="${hour} : ${minute}"
                time.setText(e)
                Log.d("time","${hour} : ${minute}" )*//*

                TimePicker = TimePickerDialog(requireContext(), object : TimePickerDialog.OnTimeSetListener {
                    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                        time.setText(String.format("%d : %d", hourOfDay, minute))
                    }
                }, hour, minute, false)

                datePickerDialog.show()
                time.setText(String.format("%d : %d", hour, minute))
                TimePicker.show()*/
            }
        }
    }

}
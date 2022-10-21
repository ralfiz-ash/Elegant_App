package com.example.elegant_app.staff

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.clearFragmentResult
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentTimetableBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class TimetableFragment : Fragment(),
    TimePickerDialog.OnTimeSetListener {
    private lateinit var binding: FragmentTimetableBinding
    public var hse = mutableListOf(
        "Choose Subject",
        "History",
        "Economics",
        "Political Science",
        "Sociology",
        "English",
        "Statistics",
        "CA",
        "Accountancy",
        "Mathematics"
    )
    public var i: String? = null
    var date: String? = null
    var standard: String? = null
    var time: String? = null
    var startTime: String? = null
    var endtime: String? = null
    var schedule: String? = null

    var tutor: String? = null
    var subject: String? = null
    var div: String? = null
    var cls: String? = null
    var myHour: Int = 0
    var myMinute: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var endHour: Int = 0
    var endMinute: Int = 0
    var isStartTimeClicked=false
    var isStartTimeSet=false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding=FragmentTimetableBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSpinner_For_class()
        setupSpinner_For_division()

        setupSpinner_For_teacher()
        binding.Date.setOnClickListener() {
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
                    binding.Date.setText(selectedDate)
                },
                year, month, day
            )

            datePickerDialog.show()
        }
        binding.apply {

            Time.setOnClickListener(){
                isStartTimeClicked=true
                val timePickerDialog = TimePickerDialog(
                    requireContext(), this@TimetableFragment, hour, minute,false
                )
                timePickerDialog.show()
            }

            endTime.setOnClickListener(){
                if (isStartTimeSet) {
                    val timePickerDialog = TimePickerDialog(
                        requireContext(), this@TimetableFragment, endHour, endMinute, false
                    )
                    timePickerDialog.show()
                }
            }

            btnSignin.setOnClickListener(){
                if (validator()) {
                    schedule = "${startTime} To ${endtime}"

                    val fireStoreDatabase = FirebaseFirestore.getInstance()
                    val obj = TimetableModel(
                        date = date,
                        standard = standard,
                        subject = subject,
                        time = schedule,
                        tutor = tutor
                    ) // obj of modelclass

                    fireStoreDatabase.collection("Timetable")
                        .add(obj)
                        .addOnSuccessListener {
                            //Toast.makeText(requireContext(), "Data added", Toast.LENGTH_LONG).show()

                            /* binding.etDate.text.clear()
                             binding.etTPostmessage.text.clear()*/

                            //Log.d(TAG, "Added document with ID ${it.id}")
                        }
                        .addOnFailureListener { exception ->
                            Toast.makeText(requireContext(), "Error Occured", Toast.LENGTH_LONG)
                                .show()
                            //Log.w(TAG, "Error adding document $exception")
                        }
                    endtime = null
                    time = null
                    binding.Date.text.clear()
                    binding.endTime.text.clear()
                    Snackbar.make(it, "TimeTable Posted", Snackbar.LENGTH_LONG).show()


                }
            }


        }


    }

    private fun setupSpinner_For_class() {
        val classNames = arrayOf("Choose Standard", "5", "6", "7", "8", "9", "10", "+1", "+2")
        val spinner = binding.Spinnerclass
        val arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, classNames)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                cls = classNames[position]
                i = cls
                setupSpinner_For_subject()
                Log.d("clselected", "onItemSelected: ${i}")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

    }

    private fun setupSpinner_For_subject() {
        Log.d("ivalue-", "${cls}")

        if (cls?.trim().equals("+1", false) || cls.equals("+2", false)) {
            val spinner = binding.Spinnersubject
            val arrayAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, hse)
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = arrayAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {

                    subject = spinner.selectedItem.toString()
                    Log.d("zx", "->: ${subject}")

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }

            }
        } else {
            val subjectNames = arrayOf(
                "Choose Subject",
                "Physics",
                "Chemistry",
                "Maths",
                "Biology",
                "English",
                "Hindi",
                "Malayalam",
                "Arabic",
                "Social",
                "Science"
            )

            val spinner = binding.Spinnersubject
            val arrayAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, subjectNames)
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = arrayAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    Log.d("class-", "${cls}")
                    subject = spinner.selectedItem.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }


    }


    private fun setupSpinner_For_division() {
        val divisionNames = arrayOf("Choose Division","A","B","C","D","E","F")
        val spinner = binding.Spinnerdivision
        val arrayAdapter =ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, divisionNames)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                div=spinner.selectedItem.toString()
                standard="${cls} ${div}"

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

    }


    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        time = "${hourOfDay}:${minute}"

        Log.d("time", "onTimeSet: hour-${hourOfDay}-Minute ${minute}")

        var formattedTime: String = when {
            hourOfDay == 0 -> {
                if (minute < 10) {
                    "${hourOfDay + 12}:0${minute} am"
                } else {
                    "${hourOfDay + 12}:${minute} am"
                }
            }
            hourOfDay > 12 -> {
                if (minute < 10) {
                    "${hourOfDay - 12}:0${minute} pm"
                } else {
                    "${hourOfDay - 12}:${minute} pm"
                }
            }
            hourOfDay == 12 -> {
                if (minute < 10) {
                    "${hourOfDay}:0${minute} pm"
                } else {
                    "${hourOfDay}:${minute} pm"
                }
            }
            else -> {
                if (minute < 10) {
                    "${hourOfDay}:${minute} am"
                } else {
                    "${hourOfDay}:${minute} am"
                }
            }
        }

        time = formattedTime
       // binding.Time.setText(time)
        if (isStartTimeClicked) {
            isStartTimeSet = true
            isStartTimeClicked = false
            binding.Time.setText(time)
            startTime = time
            Log.d("timecheck", "on->: ${startTime},${time}")
        }else
        {
            endtime=formattedTime
            Log.d("timecheck", "on->: ${endtime},${formattedTime}")
            binding.endTime.setText(endtime)

        }
    }



    private fun setupSpinner_For_teacher() {
        val spinner = binding.Spinnerteacher
        val arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, tutors)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                tutor= spinner.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

    }

    private fun validator():Boolean{
        if (date!=null && standard!=null && subject!=null && time!=null && tutor!=null && endtime!=null){
             return true
        }
        else {

            //Log.d("timetable", "validator: Submit all values")
            Toast.makeText(requireContext(), "Submit all values", Toast.LENGTH_SHORT).show()
            return false
        }
    }




}
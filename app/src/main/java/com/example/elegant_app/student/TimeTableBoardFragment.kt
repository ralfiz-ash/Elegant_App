package com.example.elegant_app.student

import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elegant_app.Adapters.TableAdapter
import com.example.elagant.pojo.TimetableModel
import com.example.elegant_app.databinding.FragmentTimeTableBoardBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

class TimeTableBoardFragment : Fragment() {
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    var ll = mutableListOf<TimetableModel>()
    lateinit var binding: FragmentTimeTableBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimeTableBoardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onViewCreated(view, savedInstanceState)
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        binding.tvChooseDate.setOnClickListener() {
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

                    binding.tvChooseDate.text = selectedDate
                },
                year, month, day
            )

            datePickerDialog.show()
        }
        binding.btnSearch.setOnClickListener {
            showData()
        }
    }

    private fun showData() {

        ll.clear()
        val db = FirebaseFirestore.getInstance()

        val sp: SharedPreferences =
            requireContext().getSharedPreferences("log", Context.MODE_PRIVATE);

        db.collection("Timetable")
            //.whereEqualTo("standard",sp.getString("div",""))
            .whereEqualTo("date", binding.tvChooseDate.text.toString())
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    ll.add(
                        TimetableModel(
                            date = document.data.get("date").toString(),
                            std = document.data.get("standard").toString(),
                            time = document.data.get("time").toString(),
                            endTime = document.data.get("endtime").toString(),
                            subject = document.data.get("subject").toString(),
                            tutor = document.data["tutor"].toString()
                        )
                    )
                }
                binding.timetableRecycle.layoutManager = LinearLayoutManager(requireContext())
                val adapter = TableAdapter(requireContext(), ll)
                binding.timetableRecycle.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }
}
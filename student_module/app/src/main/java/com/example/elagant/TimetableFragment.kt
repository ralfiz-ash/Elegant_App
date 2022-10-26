package com.example.elagant

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
import com.example.elagant.Adapters.NotifyAdapter
import com.example.elagant.Adapters.TableAdapter
import com.example.elagant.databinding.FragmentTimetableBinding
import com.example.elagant.pojo.NotifyModel
import com.example.elagant.pojo.TimetableModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

class TimetableFragment : Fragment() {
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    var ll = mutableListOf<TimetableModel>()
    lateinit var binding: FragmentTimetableBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimetableBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        binding.imgDate.setOnClickListener() {
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

                    binding.textView15.setText(selectedDate)
                },
                year, month, day
            )

            datePickerDialog.show()
        }
        binding.btnsearch.setOnClickListener {
            showData()
        }
    }

    private fun showData() {

        ll.clear()
        val db = FirebaseFirestore.getInstance()

        val sp: SharedPreferences =
            requireContext().getSharedPreferences("log", Context.MODE_PRIVATE);

        db.collection("Timetable")
            .whereEqualTo(
                "standard", sp.getString("div", "")
            ).whereEqualTo("date", binding.textView15.text.toString())
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    ll.add(
                        TimetableModel(
                            document.data.get("date").toString(),
                            document.data.get("standard").toString(),
                            document.data.get("subject").toString(),
                            document.data.get("time").toString(),
                            document.data.get("tutor").toString()
                        )
                    )
                }
                binding.timetableRecycle.layoutManager =
                    LinearLayoutManager(requireContext())
                val adapter = TableAdapter(requireContext(), ll)
                binding.timetableRecycle.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }

}
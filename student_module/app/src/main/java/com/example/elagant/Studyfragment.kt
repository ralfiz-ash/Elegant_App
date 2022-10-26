package com.example.elagant

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
import com.example.elagant.Adapters.StudyAdapter
import com.example.elagant.Adapters.TableAdapter
import com.example.elagant.databinding.FragmentStudyfragmentBinding
import com.example.elagant.databinding.FragmentTeachersBinding
import com.example.elagant.pojo.StudyModel
import com.example.elagant.pojo.TimetableModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class Studyfragment : Fragment() {
    lateinit var binding: FragmentStudyfragmentBinding
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    var ll = mutableListOf<StudyModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudyfragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        showData()
    }

    private fun showData() {

        ll.clear()
        val db = FirebaseFirestore.getInstance()

        val sp: SharedPreferences =
            requireContext().getSharedPreferences("log", Context.MODE_PRIVATE);

        db.collection("Study_Material")
            .whereEqualTo(
                "standard", sp.getString("class", "")
            )
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    ll.add(
                        StudyModel(
                            document.data.get("title").toString(),
                            document.data.get("subject").toString(),
                            document.data.get("link").toString()
                        )
                    )
                }
                binding.studyRecycle.layoutManager =
                    LinearLayoutManager(requireContext())
                val adapter = StudyAdapter(requireContext(), ll)
                binding.studyRecycle.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }
}
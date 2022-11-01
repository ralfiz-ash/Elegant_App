package com.example.elegant_app.student

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elegant_app.Adapters.DutyAdapter
import com.example.elagant.pojo.DutyModel
import com.example.elegant_app.databinding.FragmentDutiesBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class DutiesFragment : Fragment() {
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    var ll = mutableListOf<DutyModel>()
    lateinit var binding: FragmentDutiesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDutiesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fireStoreDatabase = FirebaseFirestore.getInstance()
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        showData()

    }

    private fun showData() {

        ll.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("Assignment")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    ll.add(
                        DutyModel(
                            topic = document.data.get("topic").toString(),
                            begindate = document.data.get("createDate").toString(),
                            subject = document.data.get("subject").toString(),
                            standard = document.data.get("standard").toString(),
                            enddate = document.data.get("date").toString()
                        )
                    )


                }

                binding.dutyRecycle.layoutManager = LinearLayoutManager(requireContext())
                val adapter = DutyAdapter(requireContext(), ll)
                binding.dutyRecycle.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }

}
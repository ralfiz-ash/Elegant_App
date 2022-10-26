package com.example.elagant

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elagant.Adapters.DoubtAdapter
import com.example.elagant.Adapters.TeacherAdapter
import com.example.elagant.databinding.FragmentDoubtBinding
import com.example.elagant.pojo.Doubtmodel
import com.example.elagant.pojo.TeacherModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class DoubtFragment : Fragment() {

    lateinit var binding: FragmentDoubtBinding
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    var ll = mutableListOf<Doubtmodel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDoubtBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fireStoreDatabase = FirebaseFirestore.getInstance()
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        showData()
        binding.ask.setOnClickListener {


            val obj =
                Doubtmodel(binding.edtDoubt.text.toString(), "nithin", "") // obj of modelclass

            fireStoreDatabase.collection("Doubt")
                .add(obj)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Data added", Toast.LENGTH_LONG).show()
                    binding.edtDoubt.text.clear()
                    showData()
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(requireContext(), "Error Occured", Toast.LENGTH_LONG).show()
                    //Log.w(TAG, "Error adding document $exception")
                }

            Toast.makeText(requireContext(), "Data added", Toast.LENGTH_LONG).show()
        }
    }

    private fun showData() {

        ll.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("Doubt")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    ll.add(
                        Doubtmodel(
                            document.data.get("question").toString(),
                            document.data.get("student").toString(),
                            document.data.get("answer").toString()
                        )
                    )


                }

                binding.doubtRecycle.layoutManager = LinearLayoutManager(requireContext())
                val adapter = DoubtAdapter(requireContext(), ll)
                binding.doubtRecycle.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }

}
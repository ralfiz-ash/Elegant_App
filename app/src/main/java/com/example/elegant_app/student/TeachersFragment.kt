package com.example.elegant_app.student

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elegant_app.Adapters.TeacherAdapter
import com.example.elagant.pojo.TeacherModel
import com.example.elegant_app.databinding.FragmentTeachersBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class TeachersFragment : Fragment() {
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    var ll = mutableListOf<TeacherModel>()
    lateinit var binding: FragmentTeachersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeachersBinding.inflate(layoutInflater, container, false)
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

        db.collection("Teachers")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    ll.add(
                        TeacherModel(
                            document.data.get("name").toString(),
                            document.data.get("standard").toString(),
                            document.data.get("mobile").toString(),
                            document.data.get("photo").toString(),
                            document.data.get("qualification").toString(),
                            document.data.get("medium").toString()
                        )
                    )

                }

                binding.staffRecycle.layoutManager = LinearLayoutManager(requireContext())
                val adapter = TeacherAdapter(requireContext(), ll)
                binding.staffRecycle.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }
}
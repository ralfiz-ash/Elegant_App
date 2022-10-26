package com.example.elagant

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elagant.Adapters.NotifyAdapter
import com.example.elagant.databinding.FragmentNotifyBinding
import com.example.elagant.pojo.NotifyModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class NotifyFragment : Fragment() {
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    var ll = mutableListOf<NotifyModel>()
    lateinit var binding: FragmentNotifyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotifyBinding.inflate(layoutInflater, container, false)
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

        db.collection("Teacher_Post")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    ll.add(
                        NotifyModel(
                            document.data.get("announcement").toString(),
                            document.data.get("date").toString()
                        )
                    )


                }

                binding.notifyRecycle.layoutManager = LinearLayoutManager(requireContext())
                val adapter = NotifyAdapter(requireContext(), ll)
                binding.notifyRecycle.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }

}
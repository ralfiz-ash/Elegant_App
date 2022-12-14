package com.example.elegant_app.student

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.elagant.pojo.Doubtmodel
import com.example.elagant.pojo.complaintModel
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentFeedCompBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class Feed_compFragment : Fragment() {

    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    lateinit var binding: FragmentFeedCompBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedCompBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fireStoreDatabase = FirebaseFirestore.getInstance()
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        binding.apply {
            button.setOnClickListener {
                if (binding.etFeedback.text.isNotEmpty()) {
                    etFeedback.setBackgroundResource(R.drawable.tint_tile)
                    val obj =
                        complaintModel(
                            binding.etFeedback.text.toString(),
                            "1111"
                        ) // obj of modelclass
                    fireStoreDatabase.collection("complaint").add(obj)
                        .addOnSuccessListener {
                            Toast.makeText(
                                requireContext(),
                                "complaint send successfully",
                                Toast.LENGTH_LONG
                            ).show()
                            binding.etFeedback.text.clear()
                        }
                        .addOnFailureListener { exception ->
                            Toast.makeText(requireContext(), "Error Occured", Toast.LENGTH_LONG)
                                .show()
                            //Log.w(TAG, "Error adding document $exception")
                        }

                } else {
                    etFeedback.setBackgroundResource(R.drawable.error_curved)
                }
            }
        }
    }


}
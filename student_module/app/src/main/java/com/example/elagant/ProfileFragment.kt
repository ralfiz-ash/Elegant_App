package com.example.elagant

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elagant.Adapters.NotifyAdapter
import com.example.elagant.databinding.FragmentProfileBinding
import com.example.elagant.pojo.NotifyModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.lang.Exception

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        showData()
    }

    private fun showData() {
        val sp: SharedPreferences =
            requireContext().getSharedPreferences("log", Context.MODE_PRIVATE);

        val db = FirebaseFirestore.getInstance()
        db.collection("Students")
            .whereEqualTo(
                "reg_no", sp.getString("reg_no", "")
            )
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("@@", "${document.id} => ${document.data}")
                    binding.apply {
                        NameValue.text = document.get("name").toString()
                        qualifValue.text = document.get("standard").toString()
                        expValue.text = document.get("division").toString()
                        mobileValue.text = document.get("mobile").toString()
                        emailValue.text = document.get("email").toString()
                        bloodValue.text = document.get("blood").toString()
                        adharValue.text = document.get("adhar").toString()
                        genderValue.text = document.get("gender").toString()
                        addressValue.text = document.get("address").toString()
                        divValue.text = document.get("medium").toString()
                        tvName.text = document.get("reg_no").toString()
                        try {
                            val imageRef = FirebaseStorage.getInstance()
                                .getReferenceFromUrl(document.get("photo").toString())

                            imageRef.getBytes(10 * 1024 * 1024).addOnSuccessListener {
                                val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                                userImage.setImageBitmap(bitmap)

                            }.addOnFailureListener {
                                // Handle any errors
                            }
                        } catch (e: Exception) {

                        }
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }


    }
}
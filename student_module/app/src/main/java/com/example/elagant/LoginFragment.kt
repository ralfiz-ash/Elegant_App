package com.example.elagant

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.elagant.databinding.FragmentLoginBinding
import com.example.elagant.pojo.DutyModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    var ll = mutableListOf<DutyModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fireStoreDatabase = FirebaseFirestore.getInstance()
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        binding.btnSign.setOnClickListener {
            showData()
        }

    }

    private fun showData() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Students")
            .whereEqualTo(
                "reg_no", binding.txtUname.text.toString()
            )
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {


                    if (document.get("reg_no").toString()
                            .equals(binding.txtUname.text.toString())
                    ) {
                        val sp: SharedPreferences =
                            requireContext().getSharedPreferences("log", Context.MODE_PRIVATE);
                        val ed: SharedPreferences.Editor = sp.edit()
                        ed.putString("class", document.get("standard").toString())
                        ed.putString("div", document.get("division").toString())
                        ed.putString("reg_no", document.get("reg_no").toString())
                        ed.commit()
                        Toast.makeText(requireContext(), "Login successfull", Toast.LENGTH_SHORT)
                            .show()
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToStudentHome())

                    } else {
                        Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            }.addOnFailureListener {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT)
                    .show()

            }
    }


}
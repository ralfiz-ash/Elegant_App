package com.example.elegant_app.staff

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elegant_app.R
import com.example.elegant_app.admin.FeedbackAdapter
import com.example.elegant_app.admin.ModelClass
import com.example.elegant_app.databinding.FragmentNotififyfromAdminBinding
import com.google.firebase.firestore.FirebaseFirestore

class notififyfromAdminFragment : Fragment() {
    private lateinit var binding:FragmentNotififyfromAdminBinding
    var ll = mutableListOf<ModelClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showData()
        binding=FragmentNotififyfromAdminBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
    private fun showData() {

        ll.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("admin_msgs")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                   // ll.add(ModelClass("${document.data.get("name")}").toString())
                    val a = document.data.get("msg")
                    //Log.d(TAG, "Data Name=> ${a}")
                    //val b = document.data.get("task")
                    var c: String = "$a"

                    //if (a != null && b != null) {
                    ll.add(ModelClass(c))

                    //}

                    //Log.d(TAG, "showData: ${}")
                }
               /* val myAdapter = ModelClassAdapter(requireContext(), ll)
                binding.lvData.adapter = myAdapter
                myAdapter.notifyDataSetChanged()*/
                binding.lvData.layoutManager= LinearLayoutManager(requireContext())
                val adapter= ModelClassAdapter(requireContext(),ll)
                binding.lvData.adapter=adapter
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }
}
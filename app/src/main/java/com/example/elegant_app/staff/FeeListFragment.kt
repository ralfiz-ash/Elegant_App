package com.example.elegant_app.staff

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elegant_app.R
import com.example.elegant_app.admin.StaffAdapter
import com.example.elegant_app.admin.StaffModel
import com.example.elegant_app.admin.Staff_listFragmentDirections
import com.example.elegant_app.databinding.FragmentFeeListBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FeeListFragment : Fragment() {
    private lateinit var binding:FragmentFeeListBinding
    var feeList = mutableListOf<FeeModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showData()
        binding=FragmentFeeListBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun showData() {

        feeList.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("Fee_Paid")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("id--", "${document.id} => ${document.data}")

                    feeList.add(
                        FeeModel(document.data.get("s_name").toString(),document.data.get("std").toString(),
                            document.data.get("date").toString(),document.data.get("month").toString(),
                            document.data.get("amount").toString()))

                }

                binding.rvFeeCard.layoutManager=LinearLayoutManager(requireContext())
                val adapter= FeeAdapter(requireContext(),feeList)
                binding.rvFeeCard.adapter=adapter

                /*adapter.itemClick ={
                    findNavController().navigate(Staff_listFragmentDirections.actionStaffListFragmentToStaffProfileFragment(it))

                }*/
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }


}
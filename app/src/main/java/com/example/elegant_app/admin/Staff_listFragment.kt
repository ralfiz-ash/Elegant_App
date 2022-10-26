package com.example.elegant_app.admin

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
import com.example.elegant_app.databinding.FragmentStaffListBinding
import com.example.elegant_app.staff.StudentModel
import com.example.elegant_app.staff.TeacherAdapter
import com.example.elegant_app.staff.TeacherModel
import com.google.firebase.firestore.FirebaseFirestore

class Staff_listFragment : Fragment() {
    private lateinit var binding:FragmentStaffListBinding
     var staffList = mutableListOf<StaffModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showData()
      binding=FragmentStaffListBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        }

    private fun showData() {

        staffList.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("Office_Staff")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")

                    staffList.add(
                        StaffModel(
                            name = document.data.get("name").toString(),
                            mobile = document.data.get("mobile").toString(),
                            email = document.data.get("email").toString(),
                            blood = document.data.get("blood").toString(),
                            address = document.data.get("address").toString(),
                            dob = document.data.get("dob").toString(),
                            gender = document.data.get("gender").toString(),
                            adhar = document.data.get("adhar").toString(),
                            photo = document.data.get("photo").toString()
                        )
                    )

                }

                binding.rvStaffs.layoutManager=LinearLayoutManager(requireContext())
                val adapter= StaffAdapter(requireContext(),staffList)
                binding.rvStaffs.adapter=adapter

                adapter.itemClick ={
                    findNavController().navigate(Staff_listFragmentDirections.actionStaffListFragmentToStaffProfileFragment(it))
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }
    }




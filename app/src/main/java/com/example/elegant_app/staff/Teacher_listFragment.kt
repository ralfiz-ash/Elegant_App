package com.example.elegant_app.staff

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elegant_app.databinding.FragmentTeacherListBinding
import com.google.firebase.firestore.FirebaseFirestore

class Teacher_listFragment() : Fragment() {
    private lateinit var binding: FragmentTeacherListBinding
    val teacherList = mutableListOf<TeacherModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showData()
        binding = FragmentTeacherListBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun showData() {

        teacherList.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("Teachers")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")


                    teacherList.add(TeacherModel(name = document.data.get("name").toString(), qualification = document.data.get("qualification").toString(), experience = document.data.get("experience").toString(),
                        mobile = document.data.get("mobile").toString(), email = document.data.get("email").toString(), blood = document.data.get("blood").toString(), dob = document.data.get("dob").toString(),
                        adhar = document.data.get("adhar").toString(), gender = document.data.get("gender").toString(), address = document.data.get("address").toString(), standard = document.data.get("standard").toString(),
                        division = document.data.get("division").toString(), medium = document.data.get("medium").toString(), photo = document.data.get("photo").toString(), doc_id = document.id))
                        //tutors.add((document.data.get("name").toString()))
                    //}

                    //Log.d(TAG, "showData: ${}")
                }

                binding.rvTeacherCards.layoutManager=LinearLayoutManager(requireContext())
                val adapter= TeacherAdapter(requireContext(),teacherList)
                binding.rvTeacherCards.adapter=adapter

                adapter.listItemClick = {
                    findNavController().navigate(Teacher_listFragmentDirections.actionTeacherListFragmentToTeacherProfileFragment(it))
                }


            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }
}
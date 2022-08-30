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
import com.example.elegant_app.admin.ModelClass
import com.example.elegant_app.databinding.FragmentStudentListBinding
import com.google.firebase.firestore.FirebaseFirestore

class Student_listFragment : Fragment() {
    private lateinit var binding:FragmentStudentListBinding
    var studentList = mutableListOf<StudentModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showData()
        binding=FragmentStudentListBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun showData() {

        studentList.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("Students")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                   // Log.d(ContentValues.TAG, "${document.id} => ${document.data}")

                    studentList.add(StudentModel(document.data.get("reg_no").toString(),document.data.get("name").toString(),
                        document.data.get("school").toString(),document.data.get("parent").toString(),
                        document.data.get("mobile").toString(),document.data.get("email").toString(),
                        document.data.get("blood").toString(),document.data.get("dob").toString(),
                        document.data.get("adhar").toString(),document.data.get("gender").toString(),
                        document.data.get("address").toString(),document.data.get("standard").toString(),
                        document.data.get("division").toString(),document.data.get("medium").toString(),document.data.get("photo").toString(),document.id.toString()))
                }

                binding.rvStudentCards.layoutManager=LinearLayoutManager(requireContext())
                val adapter= StudentAdapter(requireContext(),studentList)
                binding.rvStudentCards.adapter=adapter

                adapter.student_item_click = {
                    findNavController().navigate(Student_listFragmentDirections.actionStudentListFragmentToStudentProfileFragment(it))
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }

    }

}
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
import com.example.elegant_app.databinding.FragmentAlumniBinding
import com.example.elegant_app.databinding.FragmentStudentListBinding
import com.google.firebase.firestore.FirebaseFirestore

class AlumniFragment : Fragment() {
    private lateinit var binding:FragmentAlumniBinding
    var alumniStudentList = mutableListOf<StudentModel>()
    var alumniTutorList = mutableListOf<TeacherModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       binding=FragmentAlumniBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonStudent.setOnClickListener(){
            showStudentAlumni()
            binding.rvStudentCards.visibility=View.VISIBLE
            binding.rvTeacherCards.visibility=View.GONE
        }
        binding.buttonTutor.setOnClickListener(){
            showTutorAlumni()
            binding.rvStudentCards.visibility=View.GONE
            binding.rvTeacherCards.visibility=View.VISIBLE
        }

    }

    private fun showStudentAlumni() {

        alumniStudentList.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("Alumni_Students")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    // Log.d(ContentValues.TAG, "${document.id} => ${document.data}")

                    alumniStudentList.add(StudentModel(document.data.get("reg_no").toString(),document.data.get("name").toString(),
                        document.data.get("school").toString(),document.data.get("parent").toString(),
                        document.data.get("mobile").toString(),document.data.get("email").toString(),
                        document.data.get("blood").toString(),document.data.get("dob").toString(),
                        document.data.get("adhar").toString(),document.data.get("gender").toString(),
                        document.data.get("address").toString(),document.data.get("standard").toString(),
                        document.data.get("division").toString(),document.data.get("medium").toString(),document.data.get("photo").toString(),document.id.toString()))
                }

                binding.rvStudentCards.layoutManager= LinearLayoutManager(requireContext())
                val adapter= StudentAdapter(requireContext(),alumniStudentList)
                binding.rvStudentCards.adapter=adapter

                adapter.student_item_click = {
                    findNavController().navigate(AlumniFragmentDirections.actionAlumniFragmentToStudentProfileFragment(it))
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }

    }

    private fun showTutorAlumni() {

        alumniTutorList.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("Alumni_Teachers")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")


                    alumniTutorList.add(TeacherModel(name = document.data.get("name").toString(), qualification = document.data.get("qualification").toString(), experience = document.data.get("experience").toString(),
                        mobile = document.data.get("mobile").toString(), email = document.data.get("email").toString(), blood = document.data.get("blood").toString(), dob = document.data.get("dob").toString(),
                        adhar = document.data.get("adhar").toString(), gender = document.data.get("gender").toString(), address = document.data.get("address").toString(), standard = document.data.get("standard").toString(),
                        division = document.data.get("division").toString(), medium = document.data.get("medium").toString(), photo = document.data.get("photo").toString(), doc_id = document.id))
                    //tutors.add((document.data.get("name").toString()))
                    //}

                    //Log.d(TAG, "showData: ${}")
                }

                binding.rvTeacherCards.layoutManager=LinearLayoutManager(requireContext())
                val adapter= TeacherAdapter(requireContext(),alumniTutorList)
                binding.rvTeacherCards.adapter=adapter

                adapter.listItemClick = {
                    findNavController().navigate(AlumniFragmentDirections.actionAlumniFragmentToTeacherProfileFragment(it))
                }


            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }

}



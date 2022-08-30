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
import com.example.elegant_app.databinding.FragmentStaffHomeBinding
import com.google.firebase.firestore.FirebaseFirestore

public var tutors= mutableListOf<String>()
public var students= mutableListOf<String>()
public var staffs= mutableListOf<String>()
public var exams= mutableListOf<String>()
class Staff_HomeFragment : Fragment() {
    private lateinit var binding:FragmentStaffHomeBinding
    var actionMessage: Boolean=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentStaffHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showData()
        showStudents()
        showStaffs()

        Log.d("tutors", "onViewCreated: ${tutors}")
        binding.apply {


          /*  tutors?.size?.let { item1TeacherCount.setText(it).toString()}
            staffs?.size?.let { item1StaffCount.setText(it).toString() }*/
            btnFee.setOnClickListener(){
                findNavController().navigate(Staff_HomeFragmentDirections.actionStaffHomeFragmentToFeeMgtFragment())
            }
            btnTimetable.setOnClickListener(){
                findNavController().navigate(Staff_HomeFragmentDirections.actionStaffHomeFragmentToTimetableFragment())
            }
            btnRegStudent.setOnClickListener(){
                findNavController().navigate(Staff_HomeFragmentDirections.actionStaffHomeFragmentToRegisterStdntFragment())
            }

            btnRegTeacher.setOnClickListener(){
               findNavController().navigate(Staff_HomeFragmentDirections.actionStaffHomeFragmentToRegisterTchrFragment3())
            }

            btnAnnouncement.setOnClickListener(){
                findNavController().navigate(Staff_HomeFragmentDirections.actionStaffHomeFragmentToStaffPostFragment())
            }

            btnTeacherlist.setOnClickListener(){
                findNavController().navigate(Staff_HomeFragmentDirections.actionStaffHomeFragmentToTeacherListFragment())
            }

            btnStudentlist.setOnClickListener(){
                findNavController().navigate(Staff_HomeFragmentDirections.actionStaffHomeFragmentToStudentListFragment())

            }
            btnMsgToTeacher.setOnClickListener(){
                findNavController().navigate(Staff_HomeFragmentDirections.actionStaffHomeFragmentToTeacherListFragment())
            }

            btnExam.setOnClickListener(){
              findNavController().navigate(Staff_HomeFragmentDirections.actionStaffHomeFragmentToExamFragment())
            }

            ivStaffnotify.setOnClickListener (){
                findNavController().navigate(Staff_HomeFragmentDirections.actionStaffHomeFragmentToNotififyfromAdminFragment())
            }
            btnAds.setOnClickListener(){
                findNavController().navigate(Staff_HomeFragmentDirections.actionStaffHomeFragmentToAdvertisementFragment())
            }

            btnAlumni.setOnClickListener(){
                findNavController().navigate(Staff_HomeFragmentDirections.actionStaffHomeFragmentToAlumniFragment())
            }


        }
    }
    private fun showData() {

        tutors.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("Teachers")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    //Log.d("idfirebase", "${document.id} => ${document.data}")

                    tutors.add((document.data.get("name").toString()))
                    Log.d("tutors", "${tutors.size}")
                    //}

                    //Log.d(TAG, "showData: ${}")
                }
                binding.item1TeacherCount.setText(tutors.size.toString())


            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }

    private fun showStudents() {

        students.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("Students")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    // Log.d(ContentValues.TAG, "${document.id} => ${document.data}")

                    students.add(document.data["name"].toString())
                }
                binding.item1StudCount.setText(students.size.toString())
                Log.d("students", "${students.size}")

            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
        /*binding.item1StudCount.setText(students.size.toString())*/

    }

    private fun showStaffs() {

        staffs.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("Office_Staff")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    // Log.d(ContentValues.TAG, "${document.id} => ${document.data}")

                    staffs.add(document.data["name"].toString())
                }

                binding.item1StaffCount.setText(staffs.size.toString())
                Log.d("staff", "${staffs.size}")

            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }

    }




}
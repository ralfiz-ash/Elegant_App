package com.example.elegant_app.staff

import android.app.ProgressDialog
import android.content.ContentValues
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elegant_app.databinding.FragmentTeacherListBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Teacher_listFragment() : Fragment() {
    private lateinit var binding: FragmentTeacherListBinding
    val teacherList = mutableListOf<TeacherModel>()
    private lateinit var progress: ProgressDialog
    lateinit var adapter: TeacherAdapter
    var searchText: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTeacherListBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* lifecycleScope.launch {
             progress=ProgressDialog(requireContext())
             progress.setTitle("Fetching Data....")
             progress.setMessage("Loading..")
             progress.setProgressStyle(ProgressDialog.BUTTON_POSITIVE)
             progress.show()
        delay(2000)
             progress.dismiss()
         }*/
        showData()  // showing data

        /*    binding.etSearch.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable?) {
                    if (s?.length?.equals(0) == true) {
                        showData()
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    searchText = binding.etSearch.text.toString()
                    // searchData(searchText!!)
                }
            })
            binding.searchBox.setOnClickListener(){
                if (binding.etSearch.text.isNotBlank()){
                    searchText=binding.etSearch.text.toString()
                    searchData(searchText!!)

                }
            }*/
    }

    private fun showData() {

        teacherList.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("Teachers")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")


                    teacherList.add(
                        TeacherModel(
                            name = document.data.get("name").toString(),
                            qualification = document.data.get("qualification").toString(),
                            experience = document.data.get("experience").toString(),
                            mobile = document.data.get("mobile").toString(),
                            email = document.data.get("email").toString(),
                            blood = document.data.get("blood").toString(),
                            dob = document.data.get("dob").toString(),
                            adhar = document.data.get("adhar").toString(),
                            gender = document.data.get("gender").toString(),
                            address = document.data.get("address").toString(),
                            standard = document.data.get("standard").toString(),
                            division = document.data.get("division").toString(),
                            medium = document.data.get("medium").toString(),
                            photo = document.data.get("photo").toString(),
                            doc_id = document.id
                        )
                    )
                    //tutors.add((document.data.get("name").toString()))
                    //}

                    //Log.d(TAG, "showData: ${}")
                }

                binding.rvTeacherCards.layoutManager = LinearLayoutManager(requireContext())
                adapter = TeacherAdapter(requireContext(), teacherList)
                binding.rvTeacherCards.adapter = adapter
                adapter.notifyDataSetChanged()

                adapter.listItemClick = {
                    findNavController().navigate(
                        Teacher_listFragmentDirections.actionTeacherListFragmentToTeacherProfileFragment(
                            it
                        )
                    )
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }

    private fun searchData(search: String) {

        teacherList.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("Teachers").whereEqualTo("name", search)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")


                    teacherList.add(
                        TeacherModel(
                            name = document.data.get("name").toString(),
                            qualification = document.data.get("qualification").toString(),
                            experience = document.data.get("experience").toString(),
                            mobile = document.data.get("mobile").toString(),
                            email = document.data.get("email").toString(),
                            blood = document.data.get("blood").toString(),
                            dob = document.data.get("dob").toString(),
                            adhar = document.data.get("adhar").toString(),
                            gender = document.data.get("gender").toString(),
                            address = document.data.get("address").toString(),
                            standard = document.data.get("standard").toString(),
                            division = document.data.get("division").toString(),
                            medium = document.data.get("medium").toString(),
                            photo = document.data.get("photo").toString(),
                            doc_id = document.id
                        )
                    )
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
package com.example.elegant_app.teacher

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
import com.example.elegant_app.databinding.FragmentViewProfileBinding
import com.google.firebase.firestore.FirebaseFirestore

class ViewProfileFragment : Fragment() {
private lateinit var binding:FragmentViewProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentViewProfileBinding.inflate(layoutInflater,container,false)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            BackArrow.setOnClickListener(){
                findNavController().navigateUp()
            }
        }
    }
    /*private fun showData() {

        taskList.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("Assignment")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    //Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                    taskList.add(
                        AssignmentModel(document.data.get("subject").toString(),document.data.get("topic").toString(),document.data.get("date").toString(),
                            document.data.get("standard").toString())
                    )

                    //}

                    //Log.d(TAG, "showData: ${}")
                }

                binding.rvTasks.layoutManager= LinearLayoutManager(requireContext())
                val adapter= AssignmentAdapter(requireContext(),taskList)
                binding.rvTasks.adapter=adapter

                *//* adapter.listItemClick = {
                     findNavController().navigate(Teacher_listFragmentDirections.actionTeacherListFragmentToTeacherProfileFragment(it))
                 }*//*


            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }*/

}
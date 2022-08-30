package com.example.elegant_app.admin

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentFeedbackBinding
import com.example.elegant_app.staff.FeeAdapter
import com.example.elegant_app.staff.FeeModel
import com.google.firebase.firestore.FirebaseFirestore

class FeedbackFragment : Fragment() {
    private lateinit var binding:FragmentFeedbackBinding
    var feedbackList = mutableListOf<feedbackModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showData()
       binding=FragmentFeedbackBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun showData() {

        feedbackList.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("complaint")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")

                    feedbackList.add(
                        feedbackModel(document.data.get("sid").toString(),document.data.get("complaint").toString())
                    )

                }

                binding.rvfeedbacks.layoutManager=LinearLayoutManager(requireContext())
                val adapter= FeedbackAdapter(requireContext(),feedbackList)
                binding.rvfeedbacks.adapter=adapter

                /*adapter.itemClick ={
                    findNavController().navigate(Staff_listFragmentDirections.actionStaffListFragmentToStaffProfileFragment(it))

                }*/
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }
}
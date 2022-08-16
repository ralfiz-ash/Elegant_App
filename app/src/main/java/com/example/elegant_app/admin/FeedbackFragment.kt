package com.example.elegant_app.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentFeedbackBinding

class FeedbackFragment : Fragment() {
    private lateinit var binding:FragmentFeedbackBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=FragmentFeedbackBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var feedback_list= arrayListOf(
            feedbackModel(1,"Student Name","Feedback of a particular student... ")
        )

        binding.rvfeedbacks.layoutManager=LinearLayoutManager(requireContext())
        val adapter=FeedbackAdapter(requireContext(),feedback_list)
        binding.rvfeedbacks.adapter=adapter

    }
}
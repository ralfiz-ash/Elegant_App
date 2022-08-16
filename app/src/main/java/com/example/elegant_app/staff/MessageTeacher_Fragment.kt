package com.example.elegant_app.staff

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentMessageTeacherBinding

class MessageTeacher_Fragment : Fragment() {
 private lateinit var binding:FragmentMessageTeacherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentMessageTeacherBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTeachers.layoutManager=LinearLayoutManager(requireContext())
       // val adapter=TeacherAdapter(requireContext())
    }


}
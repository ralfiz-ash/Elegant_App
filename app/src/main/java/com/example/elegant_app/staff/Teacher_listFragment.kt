package com.example.elegant_app.staff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elegant_app.databinding.FragmentTeacherListBinding
import com.example.elegant_app.teacher.StudentModel
import com.example.elegant_app.teacher.TStudent_list_Adapter

class Teacher_listFragment() : Fragment() {
    private lateinit var binding: FragmentTeacherListBinding

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

        val tutor_list = arrayListOf(
            TeacherModel(1, "Vijitha", "B.Ed", 3.5f,),
            TeacherModel(2, "Arshad", "B.Com", 2.5f,),
            TeacherModel(3, "Rashid", "BTech", 3f,),
            TeacherModel(4, "Manya", "BSc", 2f, "", "manya@gmail.com")
        )
        binding.rvTeacherCards.layoutManager = LinearLayoutManager(requireContext())
        val adapter = TeacherAdapter(requireContext(), tutor_list)
        binding.rvTeacherCards.adapter = adapter

        adapter.listItemClick = { it ->
            findNavController().navigate(Teacher_listFragmentDirections.actionTeacherListFragmentToTeacherProfileFragment())

        }
    }
}
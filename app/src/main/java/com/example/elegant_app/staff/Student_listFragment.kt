package com.example.elegant_app.staff

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentStudentListBinding

class Student_listFragment : Fragment() {
    private lateinit var binding:FragmentStudentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentStudentListBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val students= arrayListOf(
            StudentModel(1,"Student 1","OHSS","","9087667750"),
            StudentModel(2,"Student 2","VHSS","","","t@gmail.com"),
            StudentModel(3,"Student 3","PTB HSS","",""),
            StudentModel(4,"Student 4","OHSS","","")
        )

        binding.rvStudentCards.layoutManager=LinearLayoutManager(requireContext())
        val adapter= StudentAdapter(requireContext(),students)
        binding.rvStudentCards.adapter=adapter

        adapter.student_item_click= { it ->
//action
        }
    }



}
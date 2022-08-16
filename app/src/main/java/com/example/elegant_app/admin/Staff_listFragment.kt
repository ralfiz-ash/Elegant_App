package com.example.elegant_app.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentStaffListBinding

class Staff_listFragment : Fragment() {
    private lateinit var binding:FragmentStaffListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding=FragmentStaffListBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var staff_list= arrayListOf(
            StaffModel(1,"Staff_1",),
            StaffModel(2,"Staff_2",),
            StaffModel(3,"Staff_3",)

        )

        binding.rvStaffs.layoutManager=LinearLayoutManager(requireContext())
        val adapter=StaffAdapter(requireContext(),staff_list)
        binding.rvStaffs.adapter=adapter

        adapter.itemClick = { it ->

        }
    }


}
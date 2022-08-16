package com.example.elegant_app.staff

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentStaffHomeBinding


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
        binding.apply {
            btnFee.setOnClickListener(){
                findNavController().navigate(Staff_HomeFragmentDirections.actionStaffHomeFragmentToFeeMgtFragment())
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

        }
    }


}
package com.example.elegant_app.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentAdminhomeBinding

class Admin_HomeFragment : Fragment() {
    private lateinit var binding:FragmentAdminhomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=FragmentAdminhomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            card1Staffregn.setOnClickListener(){
                findNavController().navigate(Admin_HomeFragmentDirections.actionAdminHomeFragmentToRegisterstaffFragment())

            }

            card2Msgstaff.setOnClickListener(){
                findNavController().navigate(Admin_HomeFragmentDirections.actionAdminHomeFragmentToConnectStaffFragment())

            }

            card3Tutors.setOnClickListener(){
                findNavController().navigate(Admin_HomeFragmentDirections.actionAdminHomeFragmentToTeacherListFragment())

            }

            card4Students.setOnClickListener(){
                findNavController().navigate(Admin_HomeFragmentDirections.actionAdminHomeFragmentToStudentListFragment())

            }

            card5Staffs.setOnClickListener(){
                findNavController().navigate(Admin_HomeFragmentDirections.actionAdminHomeFragmentToStaffListFragment())

            }

            card6Fees.setOnClickListener(){
                findNavController().navigate(Admin_HomeFragmentDirections.actionAdminHomeFragmentToFeeListFragment())

            }

            card7Feedback.setOnClickListener(){
                findNavController().navigate(Admin_HomeFragmentDirections.actionAdminHomeFragmentToFeedbackFragment())

            }
        }

    }

}
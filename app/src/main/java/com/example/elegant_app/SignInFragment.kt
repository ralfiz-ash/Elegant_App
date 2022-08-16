package com.example.elegant_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.elegant_app.databinding.FragmentSignInBinding
import com.example.elegant_app.databinding.FragmentTHomeBinding


class SignInFragment : Fragment() {
    private lateinit var binding:FragmentSignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSignInBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignin.setOnClickListener(){
           findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToTHomeFragment())
        }
        binding.apply {
            clGoogleimg.setOnClickListener(){
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToStaffHomeFragment())
            }

            clAppleeimg.setOnClickListener(){
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToAdminHomeFragment())
            }
            clFbimg.setOnClickListener(){
               // findNavController().navigate(SignInFragmentDirections.)
            }
        }
    }


}
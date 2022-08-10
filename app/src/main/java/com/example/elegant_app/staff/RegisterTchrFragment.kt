package com.example.elegant_app.staff

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentRegisterTchrBinding


class RegisterTchrFragment : Fragment() {
    private lateinit var binding:FragmentRegisterTchrBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentRegisterTchrBinding.inflate(layoutInflater,container,false)
        return (binding.root)
    }



}
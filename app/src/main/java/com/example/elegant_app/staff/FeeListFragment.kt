package com.example.elegant_app.staff

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentFeeListBinding

class FeeListFragment : Fragment() {
    private lateinit var binding:FragmentFeeListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentFeeListBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fee_list = arrayListOf(
           FeeModel(1,"Ashiq","10 B",500f,"18/7/2022","August"),
           FeeModel(1,"Zenith","10 D",500f,"10/8/2022","August"),
           FeeModel(1,"Bose","10 B",500f,"1/8/2022","July"),
           FeeModel(1,"Leo","10 B",500f,"2/8/2022","October"),
        )
        binding.rvFeeCard.layoutManager = LinearLayoutManager(requireContext())
        val adapter = FeeAdapter(requireContext(), fee_list)
        binding.rvFeeCard.adapter = adapter

    }


}
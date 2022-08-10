package com.example.elegant_app.teacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentQueryBinding


class QueryFragment : Fragment() {
private lateinit var binding: FragmentQueryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding=FragmentQueryBinding.inflate(layoutInflater,container,false)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var list= arrayListOf(
            QueryModel(1,"Problem","description.........."),
            QueryModel(2,"Picture","description.........."),
            QueryModel(1,"Mis match data","description.........."),

        )

        binding.rvQuery.layoutManager= LinearLayoutManager(context)
        val adapter=Query_Adapter(requireContext(),list)
        binding.rvQuery.adapter=adapter
    }
}
package com.example.elegant_app.staff

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentRegisterStdntBinding
import com.example.elegant_app.databinding.FragmentRegisterTchrBinding

class RegisterStdntFragment : Fragment() {
    private lateinit var binding: FragmentRegisterStdntBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val media= R.drawable.messi//ontextCompat.getDrawable(this,R.drawable.headerpic)
        Glide.with(this.binding.Logo).load(media).apply(RequestOptions.circleCropTransform()).into(binding.Logo)
        binding=FragmentRegisterStdntBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
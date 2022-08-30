package com.example.elegant_app.teacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentTHomeBinding
import kotlin.properties.Delegates

class THomeFragment : Fragment() {
   private lateinit var binding: FragmentTHomeBinding
   public var x:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding=FragmentTHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val media= ContextCompat.getDrawable(requireContext(),R.drawable.messi)
        Glide.with(this.binding.profileImage).load(media).apply(RequestOptions.circleCropTransform()).into(binding.profileImage)

        var cardlist= arrayListOf(
            TeacherCardModel(1,"Post a Message",R.color.c1),
            TeacherCardModel(2,"Assignment",R.color.c2),
            TeacherCardModel(3,"Queries",R.color.c3),
            TeacherCardModel(4,"Mark list",R.color.c4),
            TeacherCardModel(5,"Profile view",R.color.c5),
            TeacherCardModel(6,"Time Table",R.color.c6),
            TeacherCardModel(7,"Study Materials",R.color.c2)

        )


        binding.rvCards.layoutManager=GridLayoutManager(requireContext(),2)
        var adapter=TeacherCardAdapter(requireContext(),cardlist)
        binding.rvCards.adapter=adapter



        adapter.cardclick= {
            it->
            x=it.id
            when(x){
                1 ->findNavController().navigate(THomeFragmentDirections.actionTHomeFragmentToPostMessageFragment())
                2 ->findNavController().navigate(THomeFragmentDirections.actionTHomeFragmentToAssignmentFragment())
                3 ->findNavController().navigate(THomeFragmentDirections.actionTHomeFragmentToQueryFragment())
                4 ->findNavController().navigate(THomeFragmentDirections.actionTHomeFragmentToMarklistFragment())
                5 ->findNavController().navigate(THomeFragmentDirections.actionTHomeFragmentToViewProfileFragment())
                7 ->findNavController().navigate(THomeFragmentDirections.actionTHomeFragmentToUploadFragment())
            }
        }

        binding.apply {

        }

    }


}
/*
fun onClicks(item:TeacherCardModel)
{
  x= item.id

}*/

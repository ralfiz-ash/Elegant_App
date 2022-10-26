package com.example.elagant

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elagant.databinding.FragmentHomeBinding

import com.example.elagant.pojo.NotifyModel
import com.example.elagant.pojo.ViewItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class Home : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var viewPagerAdapter: MainPagerAdapter
    lateinit var viewPagerAdapter1: SubAdapter
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    var imageList = mutableListOf<ViewItem>()
    var imageList1 = mutableListOf<ViewItem>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pager.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        binding.imageView.setOnClickListener {
            findNavController().navigate(HomeDirections.actionHome2ToLoginFragment())
        }
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        showData()
        //
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()

            }
        })
    }

    private fun showData() {
        val db = FirebaseFirestore.getInstance()

        db.collection("Advertisement")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    imageList.add(
                        ViewItem(document.data.get("imageUrl").toString())
                    )


                }
                viewPagerAdapter = MainPagerAdapter(requireContext(), imageList)
                binding.pager.adapter = viewPagerAdapter
                showData1()
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }

    private fun showData1() {
        val db = FirebaseFirestore.getInstance()

        db.collection("Advertisement")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    imageList1.add(
                        ViewItem(document.data.get("imageUrl").toString())
                    )


                }

                viewPagerAdapter1 = SubAdapter(requireContext(), imageList1)
                binding.pager1.adapter = viewPagerAdapter1
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }

}


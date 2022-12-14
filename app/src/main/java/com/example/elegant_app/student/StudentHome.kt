package com.example.elegant_app.student

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
import com.example.elegant_app.Adapters.HomeNotifyAdapter
import com.example.elagant.pojo.NotifyModel
import com.example.elegant_app.databinding.FragmentStudentHomeBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class StudentHomeFragment : Fragment() {
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    var ll = mutableListOf<NotifyModel>()
    lateinit var binding: FragmentStudentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Toast.makeText(
                    requireContext(),
                    "Please click on logout to exit from this app",
                    Toast.LENGTH_SHORT
                ).show()

            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        showData()
        binding.apply {
            logoutImage.setOnClickListener {
                findNavController().navigate(StudentHomeFragmentDirections.actionStudentHomeToSignInFragment())

            }
            cExamscore.setOnClickListener {
                findNavController().navigate(StudentHomeFragmentDirections.actionStudentHomeToStudentExamFragment())
            }
            cDoubt.setOnClickListener {
                findNavController().navigate(StudentHomeFragmentDirections.actionStudentHomeToDoubtFragment())
            }
            cTimetable.setOnClickListener {
                findNavController().navigate(StudentHomeFragmentDirections.actionStudentHomeToTimeTableBoardFragment())
            }
            cAssignments.setOnClickListener {
                findNavController().navigate(StudentHomeFragmentDirections.actionStudentHomeToDutiesFragment())
            }
            cTeach.setOnClickListener {
                findNavController().navigate(StudentHomeFragmentDirections.actionStudentHomeToTeachersFragment())
            }
            cStudy.setOnClickListener {
                findNavController().navigate(StudentHomeFragmentDirections.actionStudentHomeToStudyfragment())
            }
            cFeed.setOnClickListener {
                findNavController().navigate(StudentHomeFragmentDirections.actionStudentHomeToFeedCompFragment())
            }
            cPay.setOnClickListener {
                findNavController().navigate(StudentHomeFragmentDirections.actionStudentHomeToPaymentFragment())
            }
            ivNotification.setOnClickListener {
                findNavController().navigate(StudentHomeFragmentDirections.actionStudentHomeToNotifyFragment())
            }
            ivAccount.setOnClickListener {
                findNavController().navigate(StudentHomeFragmentDirections.actionStudentHomeToStudentAccountFragment())
            }
        }
    }

    private fun showData() {

        ll.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("Announcement")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    ll.add(
                        NotifyModel(
                            document.data.get("announcement").toString(),
                            document.data.get("date").toString()
                        )
                    )


                }

                binding.newsrecycle.layoutManager = LinearLayoutManager(requireContext())
                val adapter = HomeNotifyAdapter(requireContext(), ll)
                binding.newsrecycle.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }
}
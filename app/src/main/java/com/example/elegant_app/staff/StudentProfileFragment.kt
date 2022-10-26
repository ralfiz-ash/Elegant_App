package com.example.elegant_app.staff

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentStudentProfileBinding
import com.google.firebase.storage.FirebaseStorage

class StudentProfileFragment : Fragment() {
    private lateinit var binding:FragmentStudentProfileBinding
    val args:StudentProfileFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentStudentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            if(args.data.photo=="nil")
            {
                userImage.setBackgroundResource(android.R.drawable.ic_media_pause)
            }
            else
            {
                val imageRef = args.data.photo?.let {
                    FirebaseStorage.getInstance().getReferenceFromUrl(
                        it
                    )
                }
                imageRef?.getBytes(10 * 1024 * 1024)?.addOnSuccessListener {
                    val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                    userImage.setImageBitmap(bitmap)
                }?.addOnFailureListener {
                    // Handle any errors
                }
            }

            tvName.setText(args.data.name)
            NameValue.text=(args.data.name)
            schoolValue.text=(args.data.school)
            parentValue.setText(args.data.parent)
            mobileValue.setText(args.data.mobile)
            emailValue.setText(args.data.email)
            bloodValue.setText(args.data.blood)
            addressValue.setText(args.data.address)
            dobValue.setText(args.data.dob)
            genderValue.setText(args.data.gender)
            adharValue.setText(args.data.adhar)
            classValue.setText(args.data.standard)
            divValue.setText(args.data.division)
            mediumValue.setText(args.data.medium)

        }
    }

}
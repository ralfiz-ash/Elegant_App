package com.example.elegant_app.staff

import android.content.DialogInterface
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.navArgs

import com.example.elegant_app.databinding.FragmentTeacherProfileBinding
import com.google.firebase.storage.FirebaseStorage

class TeacherProfileFragment : Fragment() {
    private lateinit var binding:FragmentTeacherProfileBinding
    val args:TeacherProfileFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentTeacherProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.apply {
        if (args.data.photo=="nil")
        {

        }
            else
        {
                val imageRef = FirebaseStorage.getInstance().getReferenceFromUrl(args.data.photo!!)
            imageRef.getBytes(10 * 1024 * 1024).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                userImage.setImageBitmap(bitmap)


            }.addOnFailureListener {
                // Handle any errors
            }

        }

            tvName.setText(args.data.name)
            NameValue.text=(args.data.name)
            qualifValue.text=(args.data.qualification)
            expValue.setText(args.data.experience)
            mobileValue.setText(args.data.mobile)
            emailValue.setText(args.data.email)
            bloodValue.setText(args.data.blood)
            dobValue.setText(args.data.dob)
            adharValue.setText(args.data.adhar)
            genderValue.setText(args.data.gender)
            addressValue.setText(args.data.address)
            inchargeValue.setText(args.data.standard)
            divValue.setText(args.data.division)
            mediumValue.setText(args.data.medium)

            btnMessage.setOnClickListener(){
                showAlert()
            }
        }

    }
    fun showAlert(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Send a message")

// Set up the input
        val input = EditText(this.requireContext())
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setHint("Type message")
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

// Set up the buttons
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            // Here you get get input text from the Edittext
            var typed_message = input.text.toString()
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }



}
package com.example.elegant_app.staff

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentRegisterStdntBinding
import com.example.elegant_app.databinding.FragmentRegisterTchrBinding
import java.util.*

class RegisterStdntFragment : Fragment() {
    private lateinit var binding: FragmentRegisterStdntBinding
    lateinit var selected_Medium: String
    lateinit var selected_Gender:String
    private val pictureCode = 101
    lateinit var Student_image:Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentRegisterStdntBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* val media= R.drawable.messi//ontextCompat.getDrawable(this,R.drawable.headerpic)
        Glide.with(this.binding.Logo).load(media).apply(RequestOptions.circleCropTransform()).into(binding.Logo)*/
        binding.apply {
            etStudentdob.setOnClickListener() {
                val c = Calendar.getInstance()

                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { view, year, monthOfYear, dayOfMonth ->

                        val Student_Dob =
                            (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                        binding.etStudentdob.setText(Student_Dob)
                    },
                    year, month, day
                )

                datePickerDialog.show()
            }

            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                selected_Medium = if (R.id.rbEM == checkedId) "English" else "Malayalam"
                Toast.makeText(requireContext(), selected_Medium, Toast.LENGTH_SHORT).show()
            }
            radioGroupGender.setOnCheckedChangeListener { group, checkedId ->
                selected_Gender = if (R.id.rbM == checkedId) "Male" else "Female"
                Toast.makeText(requireContext(), selected_Gender, Toast.LENGTH_SHORT).show()
            }
            btnLoadpicture.setOnClickListener(){
                selectImage()
            }

        }
    }
    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), pictureCode)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null && resultCode == Activity.RESULT_OK && (requestCode == pictureCode)) {
           binding.image.setImageURI(data.data)
            Student_image= data.data!!
        }

    }
}
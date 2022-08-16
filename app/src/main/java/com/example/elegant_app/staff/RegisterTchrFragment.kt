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
import android.widget.Toast
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentRegisterTchrBinding
import java.util.*
import kotlin.collections.ArrayList


class RegisterTchrFragment : Fragment() {
    private lateinit var binding:FragmentRegisterTchrBinding
    private var pictureCode = 101
   lateinit var teacher_dob: String
   lateinit var Teacher_img:Uri
   lateinit var Teacher_gender:String
    lateinit var list:ArrayList<String>
    lateinit var selectedlist :ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentRegisterTchrBinding.inflate(layoutInflater,container,false)
        list= arrayListOf("Malayalam","English")
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            binding.etTdob.setOnClickListener(){
                val c = Calendar.getInstance()

                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { view, year, monthOfYear, dayOfMonth ->

                        val selectedDate =
                            (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                        binding.etTdob.setText(selectedDate)
                        teacher_dob=selectedDate
                    },
                    year, month, day
                )

                datePickerDialog.show()
            }

            radioGroupTGender.setOnCheckedChangeListener { group, checkedId ->
               var selected_Gender = if (R.id.rbM == checkedId) "Male" else "Female"
                Toast.makeText(requireContext(), selected_Gender, Toast.LENGTH_SHORT).show()
                Teacher_gender=selected_Gender
            }

            btnTLoadpicture.setOnClickListener(){
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
            Teacher_img= data.data!!
        }

    }

}
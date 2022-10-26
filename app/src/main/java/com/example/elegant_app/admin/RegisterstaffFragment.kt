package com.example.elegant_app.admin

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentRegisterstaffBinding
import com.example.elegant_app.staff.FeeModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.*

class RegisterstaffFragment : Fragment() {
 private lateinit var binding:FragmentRegisterstaffBinding
 private lateinit var staffName:String
 private lateinit var staffMobile:String
 private lateinit var staffEmail:String
 private lateinit var staffBlood:String
 private lateinit var staffAddress:String
 private lateinit var staffDob:String
 private lateinit var staffGender:String
 private lateinit var staffAdhar:String
 private lateinit var staffImage:String
 private var pictureCode = 101
    private var staffEncodedstring:String ="nil"
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        binding=FragmentRegisterstaffBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etSdob.setOnClickListener(){

                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { view, year, monthOfYear, dayOfMonth ->

                        val selectedDate =
                            (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                        binding.etSdob.setText(selectedDate)
                        staffDob=selectedDate
                    },
                    year, month, day
                )

                datePickerDialog.show()
            }

        binding.radioGroupGender.setOnCheckedChangeListener { group, checkedId ->
            val selectedGender = if (R.id.rbM ==checkedId) "Male" else "Female"
            Toast.makeText(requireContext(), selectedGender, Toast.LENGTH_SHORT).show()

            staffGender=selectedGender
        }

        binding.CameraContainer.setOnClickListener() {
            selectImage()
        }

        binding.btnAddStaff.setOnClickListener() {
            uploadImage()

            staffName = binding.etSName.text.toString()
            staffMobile = binding.etSmob.text.toString()
            staffEmail = binding.etSemail.text.toString()
            staffBlood = binding.etSblood.text.toString()
            staffAddress = binding.etSaddress.text.toString()
            staffAdhar = binding.etSAdhar.text.toString()

            if (StaffFormValidate())
            {
                val fireStoreDatabase = FirebaseFirestore.getInstance()
                val obj = StaffModel(
                    name = staffName,
                    mobile = staffMobile,
                    email = staffEmail,
                    blood = staffBlood,
                    address = staffAddress,
                    dob = staffDob,
                    gender = staffGender,
                    adhar = staffAdhar,
                    photo = staffEncodedstring
                ) // obj of modelclass

                fireStoreDatabase.collection("Office_Staff")
                    .add(obj)
                    .addOnSuccessListener {
                        Toast.makeText(requireContext(), "Data added", Toast.LENGTH_LONG).show()
                        Snackbar.make(
                            requireView(),
                            "Staff Registration Successful",
                            Snackbar.LENGTH_LONG
                        ).show()

                        //Log.d(TAG, "Added document with ID ${it.id}")
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(requireContext(), "Error Occured", Toast.LENGTH_LONG).show()
                        //Log.w(TAG, "Error adding document $exception")
                    }

                //Snackbar.make(,"Fee added successfully", Snackbar.LENGTH_LONG).show()

            }

            binding.etSName.text.clear()
            binding.etSmob.text.clear()
            binding.etSemail.text.clear()
            binding.etSblood.text.clear()
            binding.etSaddress.text.clear()
            binding.etSAdhar.text.clear()
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
        if (requestCode == pictureCode && resultCode == Activity.RESULT_OK) {
            if(data == null || data.data == null){
                return
            }

            filePath = data.data
            try {
                var bitmap =
                    MediaStore.Images.Media.getBitmap(requireContext().contentResolver, filePath)
                //Glide.with(requireContext()).load(bitmap).into(binding.staffImage)
                Glide.with(requireContext()).load(bitmap)
                    .apply(RequestOptions.circleCropTransform()).into(binding.staffImage)
                // binding.staffImage.setImageBitmap(bitmap)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }

    private fun uploadImage(){
        if(filePath != null){
            val ref = storageReference?.child("Staff_Images/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(filePath!!)
            Log.d("url", "staff-uploadImage:${filePath} || ${ref} ->${uploadTask} ")
            staffEncodedstring= ref.toString()

        }else{
            Toast.makeText(requireContext(), "Please Upload an Image", Toast.LENGTH_SHORT).show()

        }
    }

    private fun StaffFormValidate(): Boolean{
        if (
            binding.etSName.text.isNotBlank() && binding.etSmob.text.isNotBlank() && binding.etSemail.text.isNotBlank()  &&
            binding.etSblood.text.isNotBlank()  && binding.etSaddress.text.isNotBlank() &&  staffGender.isNotBlank() &&
            binding.etSdob.text.isNotBlank() && binding.etSAdhar.text.isNotBlank()
        ) {
            return true
        }
        else
        {
            Toast.makeText(requireContext(), "All fields are mandatory", Toast.LENGTH_LONG).show()
            return false
        }

    }
}
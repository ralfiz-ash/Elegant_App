package com.example.elegant_app.staff

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.elegant_app.R
import com.example.elegant_app.admin.StaffModel
import com.example.elegant_app.databinding.FragmentRegisterTchrBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class RegisterTchrFragment : Fragment() {
    private lateinit var binding:FragmentRegisterTchrBinding
    private var pictureCode = 101
    var teacherEncodedstring:String ="nil"
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
   lateinit var teacherDob: String
   lateinit var teacherImage:Uri
     var teacherGender:String?=null
    lateinit var teacherDivisions:String
    lateinit var teacherMedium:String
    lateinit var list:ArrayList<String>
    lateinit var selectedlist :ArrayList<String>
    lateinit var teacherName: String
    lateinit var teacherQualification: String
    lateinit var teacherExperience: String
    lateinit var teacherMobile: String
    lateinit var teacherEmail: String
    lateinit var teacherBlood: String
    lateinit var teacherAddress: String
    lateinit var teacherAdhar: String
    lateinit var teacherClass: String
    var listMedium:MutableList<String> = arrayListOf()
    var listDivision:MutableList<String> = arrayListOf()
    var b:Any?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        binding=FragmentRegisterTchrBinding.inflate(layoutInflater,container,false)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mediumSelected=StringBuilder()
        var divisionSelected = StringBuilder()

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
                        teacherDob=selectedDate
                    },
                    year, month, day
                )

                datePickerDialog.show()
            }


            radioGroupTGender.setOnCheckedChangeListener { group, checkedId ->
               var selected_Gender = if (R.id.rbM == checkedId) "Male" else "Female"
                Toast.makeText(requireContext(), selected_Gender, Toast.LENGTH_SHORT).show()

               teacherGender=selected_Gender
                Log.d("gender", ":${teacherGender}")
            }

            binding.rbMM.setOnCheckedChangeListener { _, isChecked ->
             if(isChecked)
             {
                 listMedium.add(binding.rbMM.text.toString())

             }else
             {
                 if (listMedium.isNotEmpty()) {

                  if ( listMedium.contains(binding.rbMM.text.toString()))
                  {
                      listMedium.remove(binding.rbMM.text.toString())
                  }
                 }
             }

                println("@SIZE"+listMedium.size)
            }

            binding.rbEM.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked)
                {
                    listMedium.add(binding.rbEM.text.toString())

                }else
                {
                    if (listMedium.isNotEmpty()) {

                        if ( listMedium.contains(binding.rbEM.text.toString()))
                        {
                            listMedium.remove(binding.rbEM.text.toString())
                        }
                    }
                }

                println("@SIZE" + listMedium.size)
            }

            //list for taking fn return of listdivision
            var a: List<Any> = listOf(gettingDivision())

            CameraContainer.setOnClickListener() {
                selectImage()
            }

            btnAddTeacher.setOnClickListener() {

                uploadImage()
                teacherName = etTname.text.toString()
                teacherQualification = etTqualif.text.toString()
                teacherExperience = etTexperience.text.toString()
                teacherMobile = etTmob.text.toString()
                teacherEmail = etTemail.text.toString()
                teacherBlood = etTblood.text.toString()
                teacherAddress = etTaddress.text.toString()
                teacherAdhar = etTAdhar.text.toString()
                teacherClass = etTinchargeclass.text.toString()

                for (i in 0 until listMedium.size) {
                    if (listMedium.size == 1) {
                        mediumSelected.append(listMedium[i])
                    } else {
                        mediumSelected.append(listMedium[i])
                        if (i != 1) {
                            mediumSelected.append(",")
                        }
                    }

                }

                teacherMedium = mediumSelected.toString() // get in medium
                //Log.d("medium", "checkboxMedium:${teacherMedium} ")

                //get in division
                for (i in 0 until a.size) {
                    if (listDivision.size == 1) {
                        divisionSelected.append(a[i])
                    } else {
                        divisionSelected.append(a[i])
                        if (i != a.size - 1) {
                            divisionSelected.append(",")
                        }
                    }

                }

                teacherDivisions = divisionSelected.toString() // get in division
                // Log.d("div", "List== ${divisionSelected}\nc:${teacherDivisions} ")

                if(TeacherFormValidate()) {

                    val fireStoreDatabase = FirebaseFirestore.getInstance()
                    val obj = TeacherModel(name = teacherName, qualification = teacherQualification, experience = teacherExperience, mobile = teacherMobile, email = teacherEmail, blood = teacherBlood, address = teacherAddress, dob = teacherDob, gender = teacherGender, adhar = teacherAdhar, standard = teacherClass,
                        medium = teacherMedium, division = teacherDivisions, photo = teacherEncodedstring)
                    fireStoreDatabase.collection("Teachers")
                        .add(obj)
                        .addOnSuccessListener {
                            Toast.makeText(requireContext(), "Data added", Toast.LENGTH_LONG).show()

                            //Log.d(TAG, "Added document with ID ${it.id}")
                        }
                        .addOnFailureListener { exception ->
                            Toast.makeText(requireContext(), "Error Occured", Toast.LENGTH_LONG).show()
                            //Log.w(TAG, "Error adding document $exception")
                        }

                    Snackbar.make(requireView(), "Tutor Added Successfully", Snackbar.LENGTH_LONG)
                        .show()

                }



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
        if (requestCode == pictureCode && resultCode == Activity.RESULT_OK) {
            if(data == null || data.data == null){
                return
            }

            filePath = data.data
            try {

                var bitmap =
                    MediaStore.Images.Media.getBitmap(requireContext().contentResolver, filePath)
                binding.image.setImageBitmap(bitmap)

                //val media= ContextCompat.getDrawable(requireContext(),R.drawable.messi)
                Glide.with(this.binding.image).load(bitmap)
                    .apply(RequestOptions.circleCropTransform()).into(binding.image)


            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }

    private fun uploadImage(){
        if(filePath != null) {
            val ref = storageReference?.child("Teacher_Images/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(filePath!!)
            Log.d("url", "teacher-uploadImage:${filePath} || ${ref} ->${uploadTask} ")
            teacherEncodedstring = ref.toString()
            Toast.makeText(requireContext(), "Picture Uploaded", Toast.LENGTH_SHORT).show()
            //binding.successText.alpha=1f
            //binding.btnUpload.alpha=0f

        }else{
            Toast.makeText(requireContext(), "Please Upload an Image", Toast.LENGTH_SHORT).show()
            //binding.btnUpload.alpha=1f
        }
    }

    private fun gettingDivision(): List<String>{

        var values=StringBuilder()
        binding.cbA.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked)
            {
                listDivision.add(binding.cbA.text.toString())

            }else
            {
                if (listDivision.isNotEmpty()) {

                    if ( listDivision.contains(binding.cbA.text.toString()))
                    {
                        listDivision.remove(binding.cbA.text.toString())
                    }
                }
            }

            println("lisidiv"+listDivision.size)
        }
        binding.cbB.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked)
            {
                listDivision.add(binding.cbB.text.toString())

            }else
            {
                if (listDivision.isNotEmpty()) {

                    if ( listDivision.contains(binding.cbB.text.toString()))
                    {
                        listDivision.remove(binding.cbB.text.toString())
                    }
                }
            }

            println("@SIZE"+listDivision.size)
        }
        binding.cbC.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked)
            {
                listDivision.add(binding.cbC.text.toString())

            }else
            {
                if (listDivision.isNotEmpty()) {

                    if ( listDivision.contains(binding.cbC.text.toString()))
                    {
                        listDivision.remove(binding.cbC.text.toString())
                    }
                }
            }

            println("@SIZE"+listDivision.size)
        }
        binding.cbD.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked)
            {
                listDivision.add(binding.cbD.text.toString())

            }else
            {
                if (listDivision.isNotEmpty()) {

                    if ( listDivision.contains(binding.cbD.text.toString()))
                    {
                        listDivision.remove(binding.cbD.text.toString())
                    }
                }
            }

            println("@SIZE"+listDivision.size)
        }
        binding.cbE.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked)
            {
                listDivision.add(binding.cbE.text.toString())

            }else
            {
                if (listDivision.isNotEmpty()) {

                    if ( listDivision.contains(binding.cbE.text.toString()))
                    {
                        listDivision.remove(binding.cbE.text.toString())
                    }
                }
            }

            println("@SIZE"+listDivision.size)
        }
        binding.cbF.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked)
            {
                listDivision.add(binding.cbF.text.toString())

            }else
            {
                if (listDivision.isNotEmpty()) {

                    if ( listDivision.contains(binding.cbF.text.toString()))
                    {
                        listDivision.remove(binding.cbF.text.toString())
                    }
                }
            }

            println("@SIZE"+listDivision.size)
        }
        binding.cbG.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked)
            {
                listDivision.add(binding.cbG.text.toString())

            }else
            {
                if (listDivision.isNotEmpty()) {

                    if (listDivision.contains(binding.cbG.text.toString())) {
                        listDivision.remove(binding.cbG.text.toString())
                    }
                }
            }

            println("@SIZE" + listDivision.size)
        }
        binding.cbHse.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                listDivision.add(binding.cbHse.text.toString())

            } else {
                if (listDivision.isNotEmpty()) {

                    if (listDivision.contains(binding.cbHse.text.toString())) {
                        listDivision.remove(binding.cbHse.text.toString())
                    }
                }
            }

            println("@SIZE" + listDivision.size)
        }
        binding.cbUG.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                listDivision.add(binding.cbUG.text.toString())

            } else {
                if (listDivision.isNotEmpty()) {

                    if (listDivision.contains(binding.cbUG.text.toString())) {
                        listDivision.remove(binding.cbUG.text.toString())
                    }
                }
            }

            println("@SIZE" + listDivision.size)
        }
        binding.cbPG.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                listDivision.add(binding.cbPG.text.toString())

            } else {
                if (listDivision.isNotEmpty()) {

                    if (listDivision.contains(binding.cbPG.text.toString())) {
                        listDivision.remove(binding.cbPG.text.toString())
                    }
                }
            }

            println("@SIZE" + listDivision.size)
        }

        return listDivision

    }

    private fun TeacherFormValidate(): Boolean{
        if (
            binding.etTname.text.isNotBlank() && binding.etTqualif.text.isNotBlank() && binding.etTexperience.text.isNotBlank() &&
            binding.etTmob.text.length == 10 && emailValidator(binding.etTemail.text.toString()) && binding.etTblood.text.isNotBlank() &&
            binding.etTaddress.text.isNotBlank() && binding.etTAdhar.text.isNotBlank() && binding.etTinchargeclass.text.isNotBlank() && teacherDob.isNotBlank() &&
            teacherGender?.isNotBlank() == true && teacherDivisions.isNotBlank() && teacherMedium.isNotBlank()
        ) {
            return true
        }
        else
        {
            Toast.makeText(requireContext(), "Please Enter Valid informations", Toast.LENGTH_LONG).show()
            return false

        }

    }

    fun emailValidator(emailToText: String) : Boolean {

        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            Toast.makeText(requireContext(), "Email Validated !", Toast.LENGTH_SHORT).show()
            return true
        } else {
            Toast.makeText(requireContext(), "Enter valid Email address !", Toast.LENGTH_SHORT).show()
            return false
        }
    }



}
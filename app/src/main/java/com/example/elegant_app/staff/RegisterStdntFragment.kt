package com.example.elegant_app.staff

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentRegisterStdntBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.*


class RegisterStdntFragment : Fragment() {
    private lateinit var binding: FragmentRegisterStdntBinding

    lateinit var selected_Medium: String
    lateinit var selected_Gender:String
     var selected_Division:String="A"
    lateinit var selected_Dob:String
    private val pictureCode = 101
    lateinit var Student_image:Uri
     var encodedstring:String ="nil"
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    lateinit var name: String
    lateinit var regno: String
    lateinit var school: String
    lateinit var parent: String
    lateinit var mobile: String
    lateinit var email: String
    lateinit var blood: String
    lateinit var address: String
    lateinit var adhar: String
    lateinit var std: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

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
                        selected_Dob=Student_Dob
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
            radioGroupDivision.setOnCheckedChangeListener{group, checkedId ->
                when(checkedId){
                    R.id.rbA ->selected_Division
                    R.id.rbB ->selected_Division
                    R.id.rbC ->selected_Division
                    R.id.rbD ->selected_Division
                    R.id.rbE ->selected_Division
                    R.id.rbFF ->selected_Division
                    R.id.rbG ->selected_Division

                }
                selected_Division="${binding.etstclass.text} ${selected_Division}"
            }

            btnLoadpicture.setOnClickListener(){
                selectImage()
            }
            btnUpload.setOnClickListener(){
                uploadImage()
            }

            btnAddStudent.setOnClickListener(){

                 name=binding.etstName.text.toString()
                 regno=binding.etRegNo.text.toString()
                school=binding.etSchool.text.toString()
                 parent=binding.etParentName.text.toString()
                 mobile=binding.etstmob.text.toString()
                 email=binding.etstemail.text.toString()
                 blood=binding.etstblood.text.toString()
                 address=binding.etstaddress.text.toString()
                 adhar=binding.etstAdhar.text.toString()
                std=binding.etstclass.text.toString()
                 //std=binding.etstclass.text.toString()

                if (studentFormValidate()) {

                    val fireStoreDatabase = FirebaseFirestore.getInstance()
                    val obj = StudentModel(reg_no = regno, name = name, school = school, parent = parent, mobile = mobile, email = email, blood = blood, address = address, dob = selected_Dob, gender = selected_Gender, adhar = adhar, standard = std, medium = selected_Medium, division = selected_Division, photo = encodedstring) // obj of modelclass

                    fireStoreDatabase.collection("Students")
                        .add(obj)
                        .addOnSuccessListener {
                            Toast.makeText(requireContext(), "Data added", Toast.LENGTH_LONG).show()
                           /* binding.etDate.text.clear()
                            binding.etTPostmessage.text.clear()*/

                            //Log.d(TAG, "Added document with ID ${it.id}")
                        }
                        .addOnFailureListener { exception ->
                            Toast.makeText(requireContext(), "Error Occured", Toast.LENGTH_LONG).show()
                            //Log.w(TAG, "Error adding document $exception")
                        }
                    Snackbar.make(it,"Student Added ", Snackbar.LENGTH_LONG).show()

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
                var bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, filePath)
                binding.image.setImageBitmap(bitmap)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }

    private fun uploadImage(){
        if(filePath != null){
            val ref = storageReference?.child("student_Images/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(filePath!!)
            Log.d("url", "uploadImage:${filePath} || ${ref} ->${uploadTask} ")
            encodedstring= ref.toString()
            binding.successText.alpha=1f
            binding.btnUpload.alpha=0f

        }else{
            Toast.makeText(requireContext(), "Please Upload an Image", Toast.LENGTH_SHORT).show()
            binding.btnUpload.alpha=1f
        }
    }


    private fun studentFormValidate(): Boolean{
        if (
            binding.etstName.text.isNotBlank() && binding.etRegNo.text.isNotBlank() && binding.etSchool.text.isNotBlank() && binding.etParentName.text.isNotBlank() &&
            binding.etstmob.text.isNotBlank() &&  emailValidator(binding.etstemail.text.toString()) && binding.etstblood.text.isNotBlank() &&
            binding.etstaddress.text.isNotBlank() && binding.etstAdhar.text.isNotBlank() && binding.etstclass.text.isNotBlank() && selected_Dob.isNotBlank() &&
            selected_Gender.isNotBlank() && selected_Division.isNotBlank() && selected_Medium.isNotBlank()
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

        // extract the entered data from the EditText
        //val emailToText = etMail.toString()

        // Android offers the inbuilt patterns which the entered
        // data from the EditText field needs to be compared with
        // In this case the entered data needs to compared with
        // the EMAIL_ADDRESS, which is implemented same below
        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            Toast.makeText(requireContext(), "Email Validated !", Toast.LENGTH_SHORT).show()
            return true
        } else {
            Toast.makeText(requireContext(), "Enter valid Email address !", Toast.LENGTH_SHORT).show()
            return false
        }
    }

}
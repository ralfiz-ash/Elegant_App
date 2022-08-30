package com.example.elegant_app.teacher

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.elegant_app.R
import com.example.elegant_app.databinding.FragmentUploadBinding
import com.example.elegant_app.staff.AnnouncementModel
import com.example.elegant_app.staff.StudyModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.util.*

class UploadFragment : Fragment() {
    private lateinit var binding:FragmentUploadBinding
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    lateinit var subject: String
    lateinit var title: String
    lateinit var link: String
    lateinit var standard: String
    //private lateinit var pdfTextView: TextView
    private lateinit var pdfUri: Uri
    lateinit var mStorage: StorageReference
    var pdfEncodedString:String="nil"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
       binding= FragmentUploadBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivUpload.setOnClickListener(){
            selectPdf()
        }



        binding.btnSignin.setOnClickListener(){

            subject=binding.etSubject.text.toString()
            standard=binding.etStandard.text.toString()
            title=binding.etTitle.text.toString()
            link=binding.etLink.text.toString()

            if(Validator())
            {

                val fireStoreDatabase = FirebaseFirestore.getInstance()
                val obj = StudyModel(standard = standard, subject = subject, title = title, link = link, pdf = pdfEncodedString) // obj of modelclass

                fireStoreDatabase.collection("Study_Material")
                    .add(obj)
                    .addOnSuccessListener {

                        Toast.makeText(requireContext(), "Uploaded", Toast.LENGTH_LONG).show()
                        binding.etSubject.text.clear()
                        binding.etStandard.text.clear()
                        binding.etTitle.text.clear()
                        binding.etLink.text.clear()


                        //Log.d(TAG, "Added document with ID ${it.id}")
                    }

                    .addOnFailureListener { exception ->
                        Toast.makeText(requireContext(), "Error Occured", Toast.LENGTH_LONG).show()
                        //Log.w(TAG, "Error adding document $exception")
                    }

            }

        }

    }

    fun Validator() :Boolean{
        if (binding.etSubject.text.isBlank() || binding.etTitle.text.isEmpty() || binding.etLink.text.isEmpty() || binding.etStandard.text.isEmpty())
        {
            //binding.etTPostmessage.setBackgroundResource(R.drawable.error_curved)
            getActivity()?.let { Snackbar.make(it.findViewById(android.R.id.content),"Enter Values", Snackbar.LENGTH_LONG).show() }
            return false
        }
        else
        {
            return true
        }
    }

    private fun selectPdf() {

        val pdfIntent = Intent(Intent.ACTION_GET_CONTENT)
        pdfIntent.type = "application/pdf"
        pdfIntent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(pdfIntent, 12)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            12 -> if (resultCode == RESULT_OK) {

                pdfUri = data?.data!!
                filePath=data?.data!!
                binding.pdfTextView.text= pdfUri.toString()
                upload()
                val uri: Uri = data?.data!!
                val uriString: String = uri.toString()
                var pdfName: String? = null
                if (uriString.startsWith("content://")) {
                    var myCursor: Cursor? = null
                    try {
                        myCursor = requireContext()!!.contentResolver.query(uri, null, null, null, null)
                        if (myCursor != null && myCursor.moveToFirst()) {
                            pdfName = myCursor.getString(myCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                            binding.pdfTextView.text = pdfName
                        }
                    } finally {
                        myCursor?.close()
                    }
                }
            }
        }
    }

    private fun upload(){
        var mRefrence= pdfUri.lastPathSegment?.let { storageReference?.child("Pdf_Materials"+UUID.randomUUID().toString()) }
        try{
            if (mRefrence != null) {
                mRefrence.putFile(pdfUri).addOnSuccessListener { taskSnapshot: UploadTask.TaskSnapshot? -> var url =taskSnapshot!!.uploadSessionUri

                    binding.etLink.setText(url.toString())
                    //binding.dwnTxt.alpha=0f
                    Toast.makeText(requireContext(),"Successfully uploaded",Toast.LENGTH_LONG).show()
                }
            }
        }
        catch (e: Exception){
            Toast.makeText(requireContext(),e.toString(),Toast.LENGTH_LONG).show()
        }

    }
    /*private fun uploadPdf(){
        if(filePath != null){
            val ref = storageReference?.child("pdf_materials/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(filePath!!)
            Log.d("url", "uploadImage:${filePath} || ${ref} ->${uploadTask} ")
            pdfEncodedString= ref.toString()


        }else{
            Toast.makeText(requireContext(), "Please Upload an pdf", Toast.LENGTH_SHORT).show()
            //binding.btnUpload.alpha=1f
        }
    }*/
}
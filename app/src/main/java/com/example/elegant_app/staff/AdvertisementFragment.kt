package com.example.elegant_app.staff

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elegant_app.admin.StaffAdapter
import com.example.elegant_app.admin.StaffModel
import com.example.elegant_app.admin.Staff_listFragmentDirections
import com.example.elegant_app.databinding.FragmentAdvertisementBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.*


class AdvertisementFragment : Fragment() {
    private lateinit var binding: FragmentAdvertisementBinding
    private var pictureCode = 101
    var adEncodedstring: String = "nil"
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    var adlist = mutableListOf<AdModel>()
    lateinit var adapter: AdAdapter
    //val args:AdvertisementFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAdvertisementBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AdAdapter(requireContext(), adlist)
        showData()
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        binding.AdImage.setOnClickListener() {
            selectImage()
        }
        binding.btnUploadAds.setOnClickListener() {
            uploadImage()
            val fireStoreDatabase = FirebaseFirestore.getInstance()
            val obj = AdModel(imageUrl = adEncodedstring) // obj of modelclass

            fireStoreDatabase.collection("Advertisement")
                .add(obj)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Advertisement Posted", Toast.LENGTH_LONG)
                        .show()
                    showData()
//                    adapter.notifyDataSetChanged()


                    //findNavController().navigate(AdvertisementFragmentDirections.actionAdvertisementFragmentSelf())

                }
                .addOnFailureListener { exception ->
                    Toast.makeText(requireContext(), "Error Occured", Toast.LENGTH_LONG).show()
                    //Log.w(TAG, "Error adding document $exception")
                }
            //Snackbar.make(it,"Student Added ", Snackbar.LENGTH_LONG).show()
        }

        adapter.itemClick = {
           // findNavController().navigate(AdvertisementFragmentDirections.actionAdvertisementFragmentSelf(it))


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
            if (data == null || data.data == null) {
                return
            }

            filePath = data.data
            try {
                /*binding.ivIcon.alpha = 1f*/
                binding.ivPicture.alpha = 1f
                var bitmap =
                    MediaStore.Images.Media.getBitmap(requireContext().contentResolver, filePath)
                binding.ivPicture.setImageBitmap(bitmap)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }

    private fun uploadImage() {
        if (filePath != null) {
            val ref = storageReference?.child("Ad_Images/" + UUID.randomUUID().toString())

            val uploadTask = ref?.putFile(filePath!!)
            Log.d("url", "ad-uploadImage:${filePath} || ${ref} ->${uploadTask} ")
            adEncodedstring = ref.toString()
            binding.ivIcon.alpha = 1f
            binding.ivPicture.alpha = 0f
            /* val myDrawable = resources.getDrawable(com.example.elegant_app.R.drawable.ic_baseline_upload_file_24)
             binding.ivPicture.setImageDrawable(myDrawable)*/
            Snackbar.make(requireView(), "File Uploaded", Snackbar.LENGTH_LONG).show()
            adapter.notifyDataSetChanged()
        } else {
            Toast.makeText(requireContext(), "Please Upload an Image", Toast.LENGTH_SHORT).show()
            //binding.btnUpload.alpha = 1f
        }
    }

    private fun showData() {

        adlist.clear()
        val db = FirebaseFirestore.getInstance()

        db.collection("Advertisement")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("docid", "${document.id} => ${document.data}")

                    adlist.add(
                        AdModel(
                            document.data.get("imageUrl").toString(),
                            document.id
                        )
                    )

                }

                binding.rvAdImages.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = AdAdapter(requireContext(), adlist)
                binding.rvAdImages.adapter = adapter

            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }


}
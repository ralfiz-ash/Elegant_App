package com.example.elagant.Adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.elagant.databinding.LayoutTeacherBinding
import com.example.elagant.pojo.TeacherModel
import com.google.firebase.storage.FirebaseStorage

class TeacherAdapter(val requireContext: Context, val ll: MutableList<TeacherModel>) :
    RecyclerView.Adapter<TeacherAdapter.MyViewholder>() {
    inner class MyViewholder(val binding: LayoutTeacherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teacherModel: TeacherModel) {
            binding.apply {
                txtName.text = teacherModel.name
                txtClass.text = "Taken Class\t:\t" + teacherModel.std
                txtLine.text = "Qualification\t:\t" + teacherModel.medium
                txtSub.text = "Taken Medium\t:\t" + teacherModel.sub
                txtPhone.text = "Contact Number\t:\t" + teacherModel.mobile
                val imageRef =
                    FirebaseStorage.getInstance().getReferenceFromUrl(teacherModel.photo!!)
                imageRef.getBytes(10 * 1024 * 1024).addOnSuccessListener {
                    val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                    imageView10.setImageBitmap(bitmap)

                }.addOnFailureListener {
                    // Handle any errors
                }
//                Glide.with(requireContext)
//                    .load(teacherModel.photo)
//                    .into(imageView10)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val binding =
            LayoutTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewholder(binding)
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.bind(ll[position])
    }

    override fun getItemCount(): Int {
        return ll.size
    }


}
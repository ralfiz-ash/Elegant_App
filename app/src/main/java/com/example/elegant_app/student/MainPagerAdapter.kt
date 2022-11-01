package com.example.elegant_app.student

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elagant.pojo.ViewItem
import com.example.elegant_app.databinding.ImageSliderItemBinding
import com.google.firebase.storage.FirebaseStorage

class MainPagerAdapter(val context: Context, val imageList: List<ViewItem>) :
    RecyclerView.Adapter<MainPagerAdapter.myviewholder>() {
    inner class myviewholder(val binding: ImageSliderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teacherModel: ViewItem) {

            binding.apply {
                val imageRef =
                    FirebaseStorage.getInstance().getReferenceFromUrl(teacherModel.imageurl)
                imageRef.getBytes(10 * 1024 * 1024).addOnSuccessListener {
                    val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                    binding.idIVImage.setImageBitmap(bitmap)

                }.addOnFailureListener {
                    // Handle any errors
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        val binding =
            ImageSliderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return myviewholder(binding)
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

}
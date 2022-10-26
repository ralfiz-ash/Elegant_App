package com.example.elagant

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.elagant.databinding.ImageSliderItemBinding
import com.example.elagant.databinding.LayoutNotifyBinding
import com.example.elagant.pojo.NotifyModel
import com.example.elagant.pojo.ViewItem
import com.google.firebase.storage.FirebaseStorage
import java.util.*

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
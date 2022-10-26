package com.example.elagant.Adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elagant.databinding.LayoutNotifyBinding
import com.example.elagant.databinding.LayoutTeacherBinding
import com.example.elagant.pojo.NotifyModel
import com.example.elagant.pojo.TeacherModel
import com.google.firebase.storage.FirebaseStorage

class NotifyAdapter(val requireContext: Context, val ll: MutableList<NotifyModel>) :
    RecyclerView.Adapter<NotifyAdapter.MyViewholder>() {
    inner class MyViewholder(val binding: LayoutNotifyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teacherModel: NotifyModel) {
            binding.apply {
                txtMsg.text = teacherModel.announcement
                txtDate.text = "created on\t:\t" + teacherModel.dates


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val binding =
            LayoutNotifyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewholder(binding)
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.bind(ll[position])
    }

    override fun getItemCount(): Int {
        return ll.size
    }


}
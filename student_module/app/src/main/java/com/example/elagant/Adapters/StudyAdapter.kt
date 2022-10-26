package com.example.elagant.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elagant.databinding.LayoutDutiesBinding
import com.example.elagant.databinding.LayoutMeterialsBinding
import com.example.elagant.pojo.DutyModel
import com.example.elagant.pojo.StudyModel

class StudyAdapter(val requireContext: Context, val ll: MutableList<StudyModel>) :
    RecyclerView.Adapter<StudyAdapter.MyViewholder>() {
    inner class MyViewholder(val binding: LayoutMeterialsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teacherModel: StudyModel) {
            binding.apply {
                txtTitle.text = "Topic\t:\t" + teacherModel.title
                txtSub.text = "Subject\t:\t" + teacherModel.subject
                txtLink.text = "Link\t:\t" + teacherModel.link

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val binding =
            LayoutMeterialsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewholder(binding)
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.bind(ll[position])
    }

    override fun getItemCount(): Int {
        return ll.size
    }


}
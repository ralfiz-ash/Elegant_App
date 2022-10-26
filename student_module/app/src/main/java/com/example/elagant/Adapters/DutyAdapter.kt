package com.example.elagant.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elagant.databinding.LayoutDoubtBinding
import com.example.elagant.databinding.LayoutDutiesBinding
import com.example.elagant.pojo.Doubtmodel
import com.example.elagant.pojo.DutyModel

class DutyAdapter(val requireContext: Context, val ll: MutableList<DutyModel>) :
    RecyclerView.Adapter<DutyAdapter.MyViewholder>() {
    inner class MyViewholder(val binding: LayoutDutiesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teacherModel: DutyModel) {
            binding.apply {
                txtTopic.text = "Topic\t:\t" + teacherModel.topic
                txtDuty.text = "Subject\t:\t" + teacherModel.subject
                txtLine.text = "Dead line\t:\t" + teacherModel.date1
                textView14.text = "Class\t:\t" + teacherModel.std
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val binding =
            LayoutDutiesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewholder(binding)
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.bind(ll[position])
    }

    override fun getItemCount(): Int {
        return ll.size
    }


}
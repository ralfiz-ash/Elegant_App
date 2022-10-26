package com.example.elagant.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elagant.databinding.LayoutDutiesBinding
import com.example.elagant.databinding.LayoutTimetableBinding
import com.example.elagant.pojo.DutyModel
import com.example.elagant.pojo.TimetableModel

class TableAdapter(val requireContext: Context, val ll: MutableList<TimetableModel>) :
    RecyclerView.Adapter<TableAdapter.MyViewholder>() {
    inner class MyViewholder(val binding: LayoutTimetableBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teacherModel: TimetableModel) {
            binding.apply {
                date.text = teacherModel.date
                classs.text = teacherModel.std
                sub.text = teacherModel.subject
                time.text = teacherModel.time
                sname.text = teacherModel.sname
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val binding =
            LayoutTimetableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewholder(binding)
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.bind(ll[position])
    }

    override fun getItemCount(): Int {
        return ll.size
    }


}
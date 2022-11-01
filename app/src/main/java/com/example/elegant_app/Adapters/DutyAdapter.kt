package com.example.elegant_app.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elagant.pojo.DutyModel
import com.example.elegant_app.databinding.LayoutDutiesBinding

class DutyAdapter(val requireContext: Context, val ll: MutableList<DutyModel>) :
    RecyclerView.Adapter<DutyAdapter.MyViewholder>() {
    inner class MyViewholder(val binding: LayoutDutiesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teacherModel: DutyModel) {
            binding.apply {
                tvTopic.text = "Topic\t:\t" + teacherModel.topic
                tvCreateDate.text = "Posted \t:\t" + teacherModel.begindate
                tvEndDate.text = "Dead line\t:\t" + teacherModel.enddate
                tvClass.text = "Class\t:\t" + teacherModel.standard
                tvSubject.text = "Subject\t:\t" + teacherModel.subject

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
